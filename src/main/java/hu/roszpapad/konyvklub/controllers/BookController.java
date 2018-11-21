package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @DeleteMapping("/user/book/{bookId}")
    private String deleteBook(@PathVariable(name = "bookId") Long bookId,
                              @PathParam(value = "userId") Long userId){
        bookService.deleteBook(bookId);
        return "redirect:/user/" + userId;
    }
}
