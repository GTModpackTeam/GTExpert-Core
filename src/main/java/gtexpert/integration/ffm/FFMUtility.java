package gtexpert.integration.ffm;

import org.jetbrains.annotations.NotNull;

import gregtech.common.ConfigHolder;

import gtexpert.api.util.GTELog;

import forestry.api.core.ForestryAPI;

public class FFMUtility {

    public static float energyModifier = ForestryAPI.activeMode.getFloatSetting("energy.demand.modifier");
    public static int feToEu = ConfigHolder.compat.energy.euToFeRatio;

    public static int timeCarpenter(int EUt) {
        return Math.round(EUt * 204 * FFMUtility.energyModifier / (100 * FFMUtility.feToEu));
    }

    public enum recipeMode {

        EASY("EASY"), // NO SUPPORT
        NORMAL("NORMAL"),
        HARD("HARD"),
        OP("OP"); // NO SUPPORT

        private final String mode;

        public String getString() {
            return this.mode;
        }

        recipeMode(final String mode) {
            this.mode = mode;
        }

        public static recipeMode safeValueOf(@NotNull String name) {
            if (name.isEmpty()) {
                GTELog.logger.error("Invalid recipe mode is empty! Set to default value.", new Throwable());
                return NORMAL;
            }

            try {
                return recipeMode.valueOf(name);
            } catch (IllegalArgumentException e) {
                GTELog.logger.error("Invalid recipe mode! Set to default value. : {}", name, e, new Throwable());
                return NORMAL;
            }
        }
    }
}
