package gtexpert.integration.ffm;

import org.jetbrains.annotations.NotNull;

import gtexpert.api.util.GTELog;

public class FFMUtility {

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
            try {
                return recipeMode.valueOf(name);
            } catch (IllegalArgumentException e) {
                GTELog.logger.error("Invalid recipe mode: " + name + ". Using NORMAL instead.");
                return NORMAL;
            }
        }
    }
}
