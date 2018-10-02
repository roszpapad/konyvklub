package hu.roszpapad.konyvklub.converters;

import hu.roszpapad.konyvklub.commands.BookCommand;
import hu.roszpapad.konyvklub.model.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book> {

    @Override
    public Book convert(BookCommand bookCommand) {

        if (bookCommand == null){
            return null;
        }

        final Book book = new Book();
        book.setId(bookCommand.getId());
        book.setPublisher(bookCommand.getPublisher());
        book.setTitle(bookCommand.getTitle());
        book.setWriter(bookCommand.getWriter());
        book.setYearOfPublishing(bookCommand.getYearOfPublishing());
        return book;
    }
}