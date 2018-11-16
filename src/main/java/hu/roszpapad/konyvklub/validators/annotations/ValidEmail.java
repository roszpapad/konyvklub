package hu.roszpapad.konyvklub.validators.annotations;

import hu.roszpapad.konyvklub.validators.ValidEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEmailValidator.class)
public @interface ValidEmail {

    String message() default "Please enter a valid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
