package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.dtos.BookToBeCreatedDTO;
import hu.roszpapad.konyvklub.exceptions.BookCantBeDeletedException;
import hu.roszpapad.konyvklub.exceptions.NotFoundException;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.OfferRepository;
import hu.roszpapad.konyvklub.repositories.TicketRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final OfferRepository offerRepository;

    private final UserRepository userRepository;

    private final TicketRepository ticketRepository;


    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException(Book.class));
    }

    @Override
    public Book createBook(BookToBeCreatedDTO bookDTO) {
        Book book = new Book();
        book.setOfferable(true);
        book.setWriter(bookDTO.getWriter());
        book.setYearOfPublishing(bookDTO.getYearOfPublishing());
        book.setPublisher(bookDTO.getPublisher());
        book.setTitle(bookDTO.getTitle());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book bookToDelete = findById(id);
        if (!bookToDelete.getOfferable()) {
            throw new BookCantBeDeletedException("A könyv nem törölhető, mert épp üzleti válaszra vár.");
        } else {
            List<Offer> offers = offerRepository.findByBookToPay(bookToDelete);
            offers.forEach(offer -> {
                Ticket ticket = offer.getTicket();
                ticket.getOffers().remove(offer);
                ticketRepository.save(ticket);
                offerRepository.delete(offer);
            });
            User owner = bookToDelete.getOwner();
            owner.getBooks().remove(bookToDelete);
            bookRepository.delete(bookToDelete);
            userRepository.save(owner);
        }
    }

    @Override
    public List<Book> getAllBooksByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class));
        return user.getBooks();
    }

    @Override
    public List<Book> getAllOfferableBooksByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class));
        return user.getBooks().stream()
                .filter(book -> book.getOfferable())
                .collect(Collectors.toList());
    }
}
