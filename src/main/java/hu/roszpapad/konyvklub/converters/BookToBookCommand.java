package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.BookCommand;
import hu.roszpapad.konyvklub.model.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToBookCommand implements Converter<Book, BookCommand> {

    @Override
    public BookCommand convert(Book book) {
        if (book == null){
            return null;
        }

        final BookCommand bookCommand = new BookCommand();
        bookCommand.setId(book.getId());
        bookCommand.setPublisher(book.getPublisher());
        bookCommand.setTitle(book.getTitle());
        bookCommand.setWriter(book.getWriter());
        bookCommand.setYearOfPublishing(book.getYearOfPublishing());
        return bookCommand;
    }
}
