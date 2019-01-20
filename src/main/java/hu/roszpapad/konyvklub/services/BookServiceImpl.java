package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.exceptions.BookCantBeDeletedException;
import hu.roszpapad.konyvklub.exceptions.BookNotFoundException;
import hu.roszpapad.konyvklub.exceptions.UserNotFoundException;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.BookRepository;
import hu.roszpapad.konyvklub.repositories.OfferRepository;
import hu.roszpapad.konyvklub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final OfferRepository offerRepository;

    private final UserRepository userRepository;


    @Override
    public Book freeBook(Book book) {

        book.setOfferable(true);
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        Book bookToUpdate = findById(book.getId());

        bookToUpdate.setYearOfPublishing(book.getYearOfPublishing());
        bookToUpdate.setWriter(book.getWriter());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setPublisher(book.getPublisher());
        bookToUpdate.setIsbn(book.getIsbn());
        return bookRepository.save(bookToUpdate);
    }

    @Override
    public void deleteBook(Long id) {
        Book bookToDelete = findById(id);
        if (!bookToDelete.getOfferable()) {
            throw new BookCantBeDeletedException();
        } else {
            List<Offer> offers = offerRepository.findByBookToPay(bookToDelete);
            offers.forEach(offer -> {
                offer.getTicket().getOffers().remove(offer);
                offerRepository.delete(offer);
            });
            User owner = bookToDelete.getOwner();
            owner.getBooks().remove(bookToDelete);
            bookRepository.delete(bookToDelete);
            userRepository.save(owner);
        }
    }

    @Override
    public Book makeBookNotOfferable(Book book) {
        book.setOfferable(false);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooksByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        return user.getBooks();
    }
}
