package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findAll();

    List<Ticket> findByBookToSellTitle(String title);
}
