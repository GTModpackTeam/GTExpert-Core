package gtexpert.recipe;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraftforge.fml.common.eventhandler.EventPriority;

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
    EventPriority priority();

    String author() default "";

    String version() default "";

    // Description of this module
    String description() default "";
}
