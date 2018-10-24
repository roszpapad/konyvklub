package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Status;
import hu.roszpapad.konyvklub.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

    Collection<Offer> findAllByStatusAndTicket(Status status, Ticket ticket);
}
