package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.OfferToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Offer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferToBeDisplayedDTOConverter implements Converter<Offer, OfferToBeDisplayedDTO> {

    private final ModelMapper modelMapper;

    @Override
    public OfferToBeDisplayedDTO toDTO(Offer entity) {
        return modelMapper.map(entity, OfferToBeDisplayedDTO.class);
    }

    @Override
    public Offer toEntity(OfferToBeDisplayedDTO dto) {
        return modelMapper.map(dto,Offer.class);
    }
}
