package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.BookToBeDisplayedDTOConverter;
import hu.roszpapad.konyvklub.dtos.BookToBeDisplayedDTO;
import hu.roszpapad.konyvklub.exceptions.BookCantBeDeletedException;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookToBeDisplayedDTOConverter bookToBeDisplayedDTOConverter;

    @DeleteMapping("/books/{bookId}")
    private ResponseEntity<?> deleteBook(@PathVariable(name = "bookId") Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().body("Könyv törölve");
    }

    @GetMapping("/users/{userId}/books")
    private ResponseEntity<List<BookToBeDisplayedDTO>> getAllBooks(@PathVariable(name = "userId") Long userId){
        List<BookToBeDisplayedDTO> bookDTOs = new ArrayList<>();
        List<Book> books = bookService.getAllBooksByUser(userId);
        books.forEach(book -> bookDTOs.add(bookToBeDisplayedDTOConverter.toDTO(book)));
        return ResponseEntity.ok().body(bookDTOs);
    }

    @GetMapping("/users/{userId}/offerableBooks")
    private ResponseEntity<List<BookToBeDisplayedDTO>> getAllOfferableBooks(@PathVariable(name = "userId") Long userId){
        List<BookToBeDisplayedDTO> bookDTOs = new ArrayList<>();
        List<Book> books = bookService.getAllOfferableBooksByUser(userId);
        books.forEach(book -> bookDTOs.add(bookToBeDisplayedDTOConverter.toDTO(book)));
        return ResponseEntity.ok(bookDTOs);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookCantBeDeletedException.class)
    public ResponseEntity<?> handleDeleteException(Exception exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
