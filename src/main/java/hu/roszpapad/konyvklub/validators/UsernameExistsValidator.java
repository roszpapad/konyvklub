package hu.roszpapad.konyvklub.validators;

import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import hu.roszpapad.konyvklub.validators.annotations.UsernameExists;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class UsernameExistsValidator implements ConstraintValidator<UsernameExists, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.isPresent();
    }

    @Override
    public void initialize(UsernameExists constraintAnnotation) {

    }
}
