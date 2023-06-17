package gtexpert.common.metatileentities.multi;

import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.capability.IMufflerHatch;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.logic.OverclockingLogic;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityAdvancedMegaBlastFurnace extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityAdvancedMegaBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.BLAST_RECIPES);
        this.recipeMapWorkable = new MetaTileEntityAdvancedMegaBlastFurnace.AdvancedMegaBlastFurnaceRecipeLogic(this);
    }

    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityAdvancedMegaBlastFurnace(this.metaTileEntityId);
    }

    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed()) {
            FluidStack liquidHeliumStack = getInputFluidInventory().drain(Materials.LiquidHelium.getFluid(Integer.MAX_VALUE), false);
            int estimatedTemp = BlockWireCoil.CoilType.TRITANIUM.getCoilTemperature() + 100 * Math.max(0, GTUtility.getTierByVoltage(this.getEnergyContainer().getInputVoltage()) - 2) + (liquidHeliumStack == null ? 0 : liquidHeliumStack.amount);
            textList.add((new TextComponentTranslation("gtexpert.multiblock.mega_blast_furnace.max_temperature", new Object[]{estimatedTemp})).setStyle((new Style()).setColor(TextFormatting.RED)));
        }

        super.addDisplayText(textList);
    }

    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
    }

    public void invalidateStructure() {
        super.invalidateStructure();
    }

    public boolean checkRecipe(@Nonnull Recipe recipe, boolean consumeIfSuccess) {
        FluidStack liquidHeliumStack = this.getInputFluidInventory().drain(Materials.LiquidHelium.getFluid(Integer.MAX_VALUE), false);
        return liquidHeliumStack != null && liquidHeliumStack.amount > 0;
    }

    protected BlockPattern createStructurePattern() {
        TraceabilityPredicate casing = states(new IBlockState[]{getCasingState()}).setMinGlobalLimited(360);
        return FactoryBlockPattern.start().aisle(new String[]{"##XXXXXXXXX##", "##XXXXXXXXX##", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############"}).aisle(new String[]{"#XXXXXXXXXXX#", "#XXXXXXXXXXX#", "###F#####F###", "###F#####F###", "###FFFFFFF###", "#############", "#############", "#############", "#############", "#############", "####FFFFF####", "#############", "#############", "#############", "#############", "#############", "#############"}).aisle(new String[]{"XXXXXXXXXXXXX", "XXXXVVVVVXXXX", "##F#######F##", "##F#######F##", "##FFFHHHFFF##", "##F#######F##", "##F#######F##", "##F#######F##", "##F#######F##", "##F#######F##", "##FFFHHHFFF##", "#############", "#############", "#############", "#############", "#############", "###TTTTTTT###"}).aisle(new String[]{"XXXXXXXXXXXXX", "XXXXXXXXXXXXX", "#F####P####F#", "#F####P####F#", "#FFHHHPHHHFF#", "######P######", "######P######", "######P######", "######P######", "######P######", "##FHHHPHHHF##", "######P######", "######P######", "######P######", "######P######", "######P######", "##TTTTPTTTT##"}).aisle(new String[]{"XXXXXXXXXXXXX", "XXVXXXXXXXVXX", "####BBPBB####", "####TITIT####", "#FFHHHHHHHFF#", "####BITIB####", "####CCCCC####", "####CCCCC####", "####CCCCC####", "####BITIB####", "#FFHHHHHHHFF#", "####BITIB####", "####CCCCC####", "####CCCCC####", "####CCCCC####", "####BITIB####", "##TTTTPTTTT##"}).aisle(new String[]{"XXXXXXXXXXXXX", "XXVXXXXXXXVXX", "####BAAAB####", "####IAAAI####", "#FHHHAAAHHHF#", "####IAAAI####", "####CAAAC####", "####CAAAC####", "####CAAAC####", "####IAAAI####", "#FHHHAAAHHHF#", "####IAAAI####", "####CAAAC####", "####CAAAC####", "####CAAAC####", "####IAAAI####", "##TTTTPTTTT##"}).aisle(new String[]{"XXXXXXXXXXXXX", "XXVXXXXXXXVXX", "###PPAAAPP###", "###PTAAATP###", "#FHPHAAAHPHF#", "###PTAAATP###", "###PCAAACP###", "###PCAAACP###", "###PCAAACP###", "###PTAAATP###", "#FHPHAAAHPHF#", "###PTAAATP###", "###PCAAACP###", "###PCAAACP###", "###PCAAACP###", "###PTAAATP###", "##TPPPMPPPT##"}).aisle(new String[]{"XXXXXXXXXXXXX", "XXVXXXXXXXVXX", "####BAAAB####", "####IAAAI####", "#FHHHAAAHHHF#", "####IAAAI####", "####CAAAC####", "####CAAAC####", "####CAAAC####", "####IAAAI####", "#FHHHAAAHHHF#", "####IAAAI####", "####CAAAC####", "####CAAAC####", "####CAAAC####", "####IAAAI####", "##TTTTPTTTT##"}).aisle(new String[]{"XXXXXXXXXXXXX", "XXVXXXXXXXVXX", "####BBPBB####", "####TITIT####", "#FFHHHHHHHFF#", "####BITIB####", "####CCCCC####", "####CCCCC####", "####CCCCC####", "####BITIB####", "#FFHHHHHHHFF#", "####BITIB####", "####CCCCC####", "####CCCCC####", "####CCCCC####", "####BITIB####", "##TTTTPTTTT##"}).aisle(new String[]{"XXXXXXXXXXXXX", "XXXXXXXXXXXXX", "#F####P####F#", "#F####P####F#", "#FFHHHPHHHFF#", "######P######", "######P######", "######P######", "######P######", "######P######", "##FHHHPHHHF##", "######P######", "######P######", "######P######", "######P######", "######P######", "##TTTTPTTTT##"}).aisle(new String[]{"XXXXXXXXXXXXX", "XXXXVVVVVXXXX", "##F#######F##", "##F#######F##", "##FFFHHHFFF##", "##F#######F##", "##F#######F##", "##F#######F##", "##F#######F##", "##F#######F##", "##FFFHHHFFF##", "#############", "#############", "#############", "#############", "#############", "###TTTTTTT###"}).aisle(new String[]{"#XXXXXXXXXXX#", "#XXXXXXXXXXX#", "###F#####F###", "###F#####F###", "###FFFFFFF###", "#############", "#############", "#############", "#############", "#############", "####FFFFF####", "#############", "#############", "#############", "#############", "#############", "#############"}).aisle(new String[]{"##XXXXXXXXX##", "##XXXXSXXXX##", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############", "#############"}).where('S', this.selfPredicate()).where('X', casing.or(this.autoAbilities(true, true, true, true, true, true, false))).where('F', frames(new Material[]{Materials.NaquadahAlloy})).where('H', casing).where('P', states(new IBlockState[]{getPipeState()})).where('B', states(new IBlockState[]{getFireboxState()})).where('I', states(new IBlockState[]{getIntakeState()})).where('T', states(new IBlockState[]{getCasingState2()})).where('V', states(new IBlockState[]{getVentState()})).where('M', abilities(new MultiblockAbility[]{MultiblockAbility.MUFFLER_HATCH})).where('C', states(MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.FUSION_CASING_MK3))).where('A', air()).where('#', any()).build();
    }

    private static IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.HIGH_TEMPERATURE_CASING);
    }

    private static IBlockState getCasingState2() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST);
    }

    private static IBlockState getFireboxState() {
        return MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TUNGSTENSTEEL_FIREBOX);
    }

    private static IBlockState getIntakeState() {
        return MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.EXTREME_ENGINE_INTAKE_CASING);
    }

    private static IBlockState getPipeState() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE);
    }

    private static IBlockState getVentState() {
        return GCYMMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT);
    }

    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtexpert.machine.electric_blast_furnace.tooltip.1", new Object[0]));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1", new Object[0]));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2", new Object[0]));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3", new Object[0]));
    }

    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return iMultiblockPart instanceof IMufflerHatch ? Textures.ROBUST_TUNGSTENSTEEL_CASING : GCYMTextures.BLAST_CASING;
    }

    @Nonnull
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.MEGA_BLAST_FURNACE_OVERLAY;
    }

    public boolean hasMufflerMechanics() {
        return true;
    }

    public boolean canBeDistinct() {
        return true;
    }

    private class AdvancedMegaBlastFurnaceRecipeLogic extends GCYMMultiblockRecipeLogic {
        
        public AdvancedMegaBlastFurnaceRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity);
        }

        @Nonnull
        protected int[] runOverclockingLogic(@Nonnull IRecipePropertyStorage propertyStorage, int recipeEUt, long maxVoltage, int duration, int maxOverclocks) {
            FluidStack liquidHeliumStack = this.getMetaTileEntity().getInputFluidInventory().drain(Materials.LiquidHelium.getFluid(Integer.MAX_VALUE), true);
            int currentTemp = BlockWireCoil.CoilType.TRITANIUM.getCoilTemperature() + 100 * Math.max(0, GTUtility.getTierByVoltage(this.getEnergyContainer().getInputVoltage()) - 2) + (liquidHeliumStack == null ? 0 : liquidHeliumStack.amount);
            return OverclockingLogic.heatingCoilOverclockingLogic(Math.abs(recipeEUt), maxVoltage, duration, maxOverclocks, currentTemp, (Integer) propertyStorage.getRecipePropertyValue(TemperatureProperty.getInstance(), 0));
        }
    }
}
