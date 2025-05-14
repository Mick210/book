package com.example.book.controllers;

import com.example.book.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.book.services.BookService;

import java.util.Collection;

@RestController
//устанавливаем базовый url
@RequestMapping("books")
public class BookController {

    //заинжектим класс который работает с книгами
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}")//GET http://localhost:8080/books/23
    public ResponseEntity<Book> getBookInfo(@PathVariable Long id) {
        Book book = bookService.findBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping //GET http://localhost:8080/books
    public ResponseEntity<Collection<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }


    @PostMapping // POST localhost:8080/books
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook((book));
    }

    @PutMapping //PUT  http://localhost:8080/books
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        Book foundBook = bookService.editBook(book);
        if (foundBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundBook);
    }

    @DeleteMapping // DELETE localhost:8080/books/23
    public Book deleteBook(@RequestBody Long id) {
        return bookService.deleteBook(id);
    }

}
