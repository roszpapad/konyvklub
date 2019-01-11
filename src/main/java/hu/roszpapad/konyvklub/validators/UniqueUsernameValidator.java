package hu.roszpapad.konyvklub.validators;

import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import hu.roszpapad.konyvklub.validators.annotations.UniqueUsername;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userRepository.findByUsername(username);
        return !user.isPresent();
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }
}
