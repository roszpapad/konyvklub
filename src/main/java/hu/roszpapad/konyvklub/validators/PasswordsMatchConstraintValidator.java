package hu.roszpapad.konyvklub.validators;

import hu.roszpapad.konyvklub.dtos.UserToBeCreatedDTO;
import hu.roszpapad.konyvklub.validators.annotations.PasswordsMatchConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchConstraintValidator implements ConstraintValidator<PasswordsMatchConstraint, UserToBeCreatedDTO> {

    @Override
    public boolean isValid(UserToBeCreatedDTO user, ConstraintValidatorContext constraintValidatorContext) {
        return user.getPassword().equals(user.getPassConfirm());
    }

    @Override
    public void initialize(PasswordsMatchConstraint constraintAnnotation) {

    }
}
