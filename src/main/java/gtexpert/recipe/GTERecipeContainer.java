package gtexpert.recipe;

import gtexpert.api.modules.IGTEModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate your {@link IGTEModule} with this for it to be automatically registered.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GTERecipeContainer {}
