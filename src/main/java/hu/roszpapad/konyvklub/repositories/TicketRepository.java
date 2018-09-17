package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
