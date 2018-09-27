package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {
}
