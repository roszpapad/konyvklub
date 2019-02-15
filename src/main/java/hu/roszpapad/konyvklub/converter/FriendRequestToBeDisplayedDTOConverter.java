package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.FriendRequestToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.FriendRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendRequestToBeDisplayedDTOConverter {

    private final ModelMapper modelMapper;

    public FriendRequestToBeDisplayedDTO toDTO(FriendRequest request){
        return modelMapper.map(request, FriendRequestToBeDisplayedDTO.class);
    }
}
