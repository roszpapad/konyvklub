package hu.roszpapad.konyvklub.validators;

import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import hu.roszpapad.konyvklub.validators.annotations.UniqueEmail;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userRepository.findByEmail(email);
        return !user.isPresent();
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }
}
