package hu.roszpapad.konyvklub.validators;

import hu.roszpapad.konyvklub.dtos.ChangePasswordRequestDTO;
import hu.roszpapad.konyvklub.exceptions.NotFoundException;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import hu.roszpapad.konyvklub.validators.annotations.ValidPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, ChangePasswordRequestDTO> {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isValid(ChangePasswordRequestDTO changePasswordRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByUsername(changePasswordRequestDTO.getUsername()).orElseThrow(() -> new NotFoundException(User.class));
        return passwordEncoder.matches(changePasswordRequestDTO.getPassword(), user.getPassword());
    }

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }
}
