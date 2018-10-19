package hu.roszpapad.konyvklub.converter;

import hu.roszpapad.konyvklub.dtos.OfferToBeDisplayed;
import hu.roszpapad.konyvklub.model.Offer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferToBeDisplayedConverter implements Converter<Offer, OfferToBeDisplayed> {

    private final ModelMapper modelMapper;

    @Override
    public OfferToBeDisplayed toDTO(Offer entity) {
        return modelMapper.map(entity, OfferToBeDisplayed.class);
    }

    @Override
    public Offer toEntity(OfferToBeDisplayed dto) {
        return modelMapper.map(dto,Offer.class);
    }
}
