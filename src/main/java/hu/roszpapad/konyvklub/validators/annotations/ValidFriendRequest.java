package hu.roszpapad.konyvklub.validators.annotations;

import hu.roszpapad.konyvklub.validators.ValidFriendRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidFriendRequestValidator.class)
public @interface ValidFriendRequest {

    String message() default "Már küldött barát kérelmet ennek a felhasználónak!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
