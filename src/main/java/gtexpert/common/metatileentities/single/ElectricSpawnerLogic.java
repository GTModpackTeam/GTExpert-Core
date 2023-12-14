package gtexpert.common.metatileentities.single;

import java.util.Collections;
import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.items.IItemHandlerModifiable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.enderio.core.client.render.BoundingBox;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.logic.OverclockingLogic;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.GTUtility;

import gtexpert.api.capability.GTEDataCodes;

import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.base.item.soulvial.ItemSoulVial;
import crazypants.enderio.machines.config.config.SpawnerConfig;
import crazypants.enderio.machines.machine.spawner.BlockPoweredSpawner;
import crazypants.enderio.util.CapturedMob;

class ElectricSpawnerLogic extends RecipeLogicEnergy {

    private boolean spawnMode = true;
    private CapturedMob mobToSpawn;
    private boolean needsRedstone = false;

    public ElectricSpawnerLogic(@NotNull MetaTileEntity metaTileEntity, Supplier<IEnergyContainer> energyContainer) {
        super(metaTileEntity, null, energyContainer);
    }

    public boolean getSpawnMode() {
        return spawnMode;
    }

    /**
     * Sets spawn mode. Also resets machine progress.
     */
    public void setSpawnMode(boolean spawnMode) {
        setSpawnModeSilent(spawnMode);
        // TODO: for some reason, this does send `WORKABLE_ACTIVE`, but client does not receive it
        // if current progress is 0
        setActive(false);
        progressTime = 0;
        setMaxProgress(0);
        recipeEUt = 0;
        setNeedsRedstone(false);
    }

    private void setSpawnModeSilent(boolean spawnMode) {
        this.spawnMode = spawnMode;
    }

    public boolean needsRedstone() {
        return needsRedstone;
    }

    private void setNeedsRedstone(boolean needsRedstone) {
        if (this.needsRedstone != needsRedstone) {
            this.needsRedstone = needsRedstone;
            writeCustomData(GTEDataCodes.NEEDS_REDSTONE, buf -> buf.writeBoolean(needsRedstone));
        }
    }

    @Override
    protected void updateRecipeProgress() {
        // We don't override `canProgressRecipe` to check this condition, as it runs only once per second,
        // and we want to stop spawn immediately when redstone is shut down
        //
        // spawn mode -> require redstone signal
        // capture mode -> always allow
        if (spawnMode && !metaTileEntity.isBlockRedstonePowered()) {
            setNeedsRedstone(true);
            return;
        }
        setNeedsRedstone(false);
        super.updateRecipeProgress();
    }

    @Override
    protected void completeRecipe() {
        if (this.spawnMode) {
            spawnEntities();
        }
        super.completeRecipe();
        this.mobToSpawn = null;
    }

    @Override
    protected void trySearchNewRecipe() {
        CapturedMob mobToSpawn = findMobToSpawn(getInputInventory());
        this.invalidInputsForRecipes = (mobToSpawn == null);
        if (isCapturedMobInvalid(mobToSpawn)) {
            return;
        }
        prepareWork(mobToSpawn);
    }

    /**
     * {@link #findRecipe} equivalent
     */
    @Nullable
    private CapturedMob findMobToSpawn(IItemHandlerModifiable inputs) {
        for (int i = 0; i < inputs.getSlots(); i++) {
            ItemStack stack = inputs.getStackInSlot(i);
            if (!(stack.getItem() instanceof ItemSoulVial)) continue;
            CapturedMob candidate = CapturedMob.create(stack);
            if (candidate != null) {
                return candidate;
            }
        }
        return null;
    }

    /**
     * {@link #prepareRecipe} equivalent
     */
    private void prepareWork(@NotNull CapturedMob mobToSpawn) {
        ItemStack outputItem = createSoulVial(mobToSpawn);
        if (outputItem.isEmpty()) {
            return;
        }
        if (!setupAndConsumeInputs(outputItem)) {
            return;
        }
        setup(mobToSpawn, outputItem);
    }

    /**
     * {@link #setupAndConsumeRecipeInputs} equivalent
     */
    private boolean setupAndConsumeInputs(@NotNull ItemStack outputItem) {
        if (!checkOverclock()) {
            return false;
        }
        if (spawnMode) {
            // When in spawn mode, nothing to consume nor to output
            return true;
        }

        if (!metaTileEntity.canVoidRecipeItemOutputs() && !GTTransferUtils.addItemsToItemHandler(getOutputInventory(),
                true, Collections.singletonList(outputItem))) {
            this.isOutputsFull = true;
            return false;
        }

        this.isOutputsFull = false;
        return consumeEmptyVial();
    }

    private boolean checkOverclock() {
        // TODO: adjust energy consumption
        int eut = 30;
        int duration = 10 * 20;
        this.overclockResults = OverclockingLogic.standardOverclockingLogic(eut, getMaximumOverclockVoltage(), duration,
                getNumberOfOCs(eut), getOverclockingDurationDivisor(),
                getOverclockingVoltageMultiplier());

        return hasEnoughPower(overclockResults);
    }

    private boolean consumeEmptyVial() {
        IItemHandlerModifiable importInventory = getInputInventory();
        for (int slot = 0; slot < importInventory.getSlots(); slot++) {
            ItemStack stack = importInventory.getStackInSlot(slot);
            if (stack.getItem() == ModObject.itemSoulVial.getItemNN() && !stack.hasTagCompound()) {
                importInventory.extractItem(slot, 1, false);
                return true;
            }
        }
        return false;
    }

    /**
     * {@link #setupRecipe} equivalent
     */
    private void setup(@NotNull CapturedMob mobToSpawn, @NotNull ItemStack outputItem) {
        this.progressTime = 1;
        setMaxProgress(overclockResults[1]);
        this.recipeEUt = overclockResults[0];
        if (!spawnMode) {
            this.itemOutputs = GTUtility.copyStackList(Collections.singletonList(outputItem));
        } else {
            this.itemOutputs = NonNullList.create();
        }
        this.fluidOutputs = NonNullList.create();
        this.mobToSpawn = mobToSpawn;

        if (this.wasActiveAndNeedsUpdate) {
            this.wasActiveAndNeedsUpdate = false;
        } else {
            this.setActive(true);
        }
    }

    private void spawnEntities() {
        for (int i = 0; i < SpawnerConfig.poweredSpawnerSpawnCount.get(); ++i) {
            trySpawnEntity(this.mobToSpawn);
        }
    }

    // adapted from crazypants.enderio.machines.machine.spawner.SpawnerLogic
    private void trySpawnEntity(CapturedMob mobToSpawn) {
        final World world = metaTileEntity.getWorld();
        final int spawnRange = SpawnerConfig.poweredSpawnerSpawnRange.get();
        Class<? extends Entity> entityClass = mobToSpawn.getEntityClass();
        if (!isAreaClear(world, entityClass, spawnRange, 2, SpawnerConfig.poweredSpawnerMaxNearbyEntities.get())) {
            return;
        }

        if (entityClass == null || !EntityLiving.class.isAssignableFrom(entityClass)) {
            return;
        }

        final BlockPos pos = metaTileEntity.getPos();
        final EntityLiving entity = (EntityLiving) createEntity(mobToSpawn);
        if (entity == null) {
            return;
        }

        for (int i = 0; i < SpawnerConfig.poweredSpawnerMaxSpawnTries.get(); i++) {
            double x = pos.getX() + .5 + (world.rand.nextDouble() - world.rand.nextDouble()) * spawnRange;
            double y = pos.getY() + world.rand.nextInt(3) - 1;
            double z = pos.getZ() + .5 + (world.rand.nextDouble() - world.rand.nextDouble()) * spawnRange;

            entity.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);

            if (canSpawnEntity(entity)) {
                world.spawnEntity(entity);
                world.playEvent(Constants.WorldEvents.MOB_SPAWNER_PARTICLES, pos, 0);
                entity.spawnExplosionParticle();
                addDependents(world, entity);
                return;
            }
        }

        cleanupUnspawnedEntity(entity);
    }

    private boolean isAreaClear(World world, Class<? extends Entity> entityClass, int spawnRangeXZ, int spawnRangeY,
                                int amount) {
        if (amount > 0) {
            return world.getEntitiesWithinAABB(entityClass,
                    new BoundingBox(metaTileEntity.getPos()).expand(spawnRangeXZ, spawnRangeY, spawnRangeXZ),
                    EntitySelectors.IS_ALIVE).size() < amount;
        }
        return true;
    }

    private boolean canSpawnEntity(EntityLiving entityliving) {
        // this is the logic from ForgeEventFactory.canEntitySpawnSpawner() with some additions
        return switch (SpawnerConfig.poweredSpawnerUseForgeSpawnChecks
                .get() ? ForgeEventFactory.canEntitySpawn(entityliving,
                        entityliving.world, (float) entityliving.posX, (float) entityliving.posY,
                        (float) entityliving.posZ,
                        null) : Event.Result.DEFAULT) {
            case ALLOW -> true;
            case DEFAULT -> {
                if (SpawnerConfig.poweredSpawnerUseVanillaSpawnChecks.get()) {
                    yield entityliving.getCanSpawnHere() && entityliving.isNotColliding(); // vanilla logic
                } else {
                    yield entityliving.isNotColliding();
                }
            }
            default -> false;
        };
    }

    private void addDependents(final @NotNull World world, final @NotNull EntityLiving entity) {
        final Entity ridingEntity = entity.getRidingEntity();
        if (ridingEntity != null) {
            ridingEntity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, 0.0F);
            if (!ridingEntity.isAddedToWorld()) {
                world.spawnEntity(ridingEntity);
            }
        }
        for (Entity passenger : entity.getPassengers()) {
            passenger.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, 0.0F);
            if (!passenger.isAddedToWorld()) {
                world.spawnEntity(passenger);
            }
        }
    }

    private void cleanupUnspawnedEntity(Entity entity) {
        if (entity != null) {
            entity.setDead();
            final Entity ridingEntity = entity.getRidingEntity();
            if (ridingEntity != null) {
                ridingEntity.setDead();
            }
            for (Entity passenger : entity.getPassengers()) {
                passenger.setDead();
            }
        }
    }

    @Override
    public void receiveCustomData(int dataId, @NotNull PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTEDataCodes.NEEDS_REDSTONE) {
            needsRedstone = buf.readBoolean();
        }
    }

    @Override
    public void writeInitialSyncData(@NotNull PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(needsRedstone);
    }

    @Override
    public void receiveInitialSyncData(@NotNull PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        needsRedstone = buf.readBoolean();
    }

    @NotNull
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        compound.setBoolean("spawnMode", this.spawnMode);
        NBTTagCompound mobTag;
        if (this.mobToSpawn == null) {
            mobTag = new NBTTagCompound();
        } else {
            mobTag = this.mobToSpawn.toNbt(null);
        }
        compound.setTag("mobToSpawn", mobTag);
        return compound;
    }

    @Override
    public void deserializeNBT(@NotNull NBTTagCompound compound) {
        super.deserializeNBT(compound);
        this.spawnMode = compound.getBoolean("spawnMode");
        this.mobToSpawn = CapturedMob.create(compound.getCompoundTag("mobToSpawn"));
    }

    @NotNull
    private ItemStack createSoulVial(@NotNull CapturedMob mobToSpawn) {
        Entity entity = createEntity(mobToSpawn);
        if (entity == null) return ItemStack.EMPTY;
        CapturedMob newMob = CapturedMob.create(entity);
        cleanupUnspawnedEntity(entity);
        if (newMob == null) return ItemStack.EMPTY;
        return newMob.toStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
    }

    /**
     * Creates new entity from captured mob.
     * Note that this might actually spawn mob in the world, see EntityZombie#onInitialSpawn.
     * Make sure to call {@link #cleanupUnspawnedEntity} if created entity does not get spawned.
     */
    @Nullable
    private Entity createEntity(CapturedMob capturedMob) {
        World world = metaTileEntity.getWorld();
        BlockPos pos = metaTileEntity.getPos();
        Entity ent = capturedMob.getEntity(world, pos, world.getDifficultyForLocation(pos), false);
        if (ent == null) {
            return null;
        }
        if (SpawnerConfig.poweredSpawnerMaxPlayerDistance.get() <= 0 &&
                SpawnerConfig.poweredSpawnerDespawnTimeSeconds.get() > 0 && ent instanceof EntityLiving) {
            ent.getEntityData().setLong(BlockPoweredSpawner.KEY_SPAWNED_BY_POWERED_SPAWNER, world.getTotalWorldTime());
            ((EntityLiving) ent).enablePersistence();
        }
        return ent;
    }

    private boolean isCapturedMobInvalid(CapturedMob capturedMob) {
        if (capturedMob == null) return true;
        Entity entityToSpawn = createEntity(capturedMob);
        if (entityToSpawn == null) return true;
        cleanupUnspawnedEntity(entityToSpawn);
        return false;
    }
}
