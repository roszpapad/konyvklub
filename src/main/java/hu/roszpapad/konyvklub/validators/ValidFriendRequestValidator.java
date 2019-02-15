package hu.roszpapad.konyvklub.validators;

import hu.roszpapad.konyvklub.dtos.FriendRequestToBeCreatedDTO;
import hu.roszpapad.konyvklub.model.FriendRequest;
import hu.roszpapad.konyvklub.repositories.FriendRequestRepository;
import hu.roszpapad.konyvklub.validators.annotations.ValidFriendRequest;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class ValidFriendRequestValidator implements ConstraintValidator<ValidFriendRequest, FriendRequestToBeCreatedDTO> {

    private final FriendRequestRepository friendRequestRepository;

    @Override
    public boolean isValid(FriendRequestToBeCreatedDTO friendRequestToBeCreatedDTO, ConstraintValidatorContext constraintValidatorContext) {
        Optional<FriendRequest> friendRequestOptional =  friendRequestRepository.findByRequestStarterAndRequestDestination(friendRequestToBeCreatedDTO.getRequestStarter(), friendRequestToBeCreatedDTO.getRequestDestination());
        return !friendRequestOptional.isPresent();
    }

    @Override
    public void initialize(ValidFriendRequest constraintAnnotation) {

    }
}
