package gtexpert.recipe;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import gtexpert.api.GTEValues;
import net.minecraftforge.fml.common.eventhandler.EventPriority;

import gtexpert.modules.GTEModules;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GTERecipe {

    // ID of this recipe module
    String moduleID();

    // ID og the container
    String containerID();

    // A human-readable name
    String name();

    // A list of this module dependencies
    String[] modDependencies() default {};

    // This module's priority
    EventPriority priority() default EventPriority.NORMAL;

    String author() default "";

    String version() default "";

    // Description of this module
    String description() default "";

    // Core Module requires one per container
    boolean coreModule() default false;
}