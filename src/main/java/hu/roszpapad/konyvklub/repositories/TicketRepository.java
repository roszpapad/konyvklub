package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Collection<Ticket> findAll();
}
