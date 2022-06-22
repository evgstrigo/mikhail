package com.example.battle.dto;

import com.example.battle.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookDTO {

    private String author;
    private String title;

    public static BookDTO bookToDto(Book book) {

        BookDTO bookDTO = new BookDTO();

        bookDTO.author = book.getAuthor();
        bookDTO.title = book.getTitle();
        return bookDTO;
    }

    public static Book dtoToBook(BookDTO bookDTO){
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        return book;
    }

}
