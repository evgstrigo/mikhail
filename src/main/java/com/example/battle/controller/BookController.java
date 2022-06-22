package com.example.battle.controller;


import com.example.battle.dto.BookDTO;
import com.example.battle.entity.Book;
import com.example.battle.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> findAll() {
        
        List<BookDTO> collect = bookRepository.findAll().stream()
                .map((book) -> BookDTO.bookToDto(book))
                .collect(Collectors.toList());
        ResponseEntity<List<BookDTO>> listResponseEntity = new ResponseEntity<>(collect, HttpStatus.OK);
        return listResponseEntity;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> create(@RequestBody BookDTO bookDTO) {
        System.out.println(bookDTO);
        Book book = BookDTO.dtoToBook(bookDTO);
        Book saved = bookRepository.save(book);
        System.out.println(book);
        ResponseEntity<Book> bookResponseEntity = new ResponseEntity<>(saved, HttpStatus.CREATED);
        return bookResponseEntity;
    }

    @PutMapping("/books")
    public void update(@RequestBody Book book) {
        Book saved = bookRepository.save(book);
    }


    @GetMapping("/books/{character}")
    public ResponseEntity<Long> findAllByFirstChar(@PathVariable String character) {
        long count = bookRepository.findAllByAuthorStartsWith(character).stream()
                .map(book -> book.getQuantity())
                .reduce(0, (quantity1, quantity2) -> quantity1 + quantity2);
        ResponseEntity<Long> listResponseEntity = new ResponseEntity<>(count, HttpStatus.OK);
        return listResponseEntity;
    }

}
