package hu.roszpapad.konyvklub.validators.annotations;

import hu.roszpapad.konyvklub.validators.PasswordsMatchConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatchConstraintValidator.class)
public @interface PasswordsMatchConstraint {

    String message() default "Passwords doesn't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
