package hu.roszpapad.konyvklub.validators.annotations;

import hu.roszpapad.konyvklub.validators.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {

    String message() default "Ezzel a felhasznalonevvel mar regisztraltak.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
