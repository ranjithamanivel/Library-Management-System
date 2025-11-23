package com.example.Library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Library.service.BookService;
import com.example.Library.entity.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> list(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable Long id){
        Book book=bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book){
        Book createdBook=bookService.addBook(book);
        return ResponseEntity.created(URI.create("/api/books/"+createdBook.getId())).body(createdBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/author")
    public ResponseEntity<Book> assignAuthor(@PathVariable Long id, @RequestBody Book updatedBook){
        Book updateBook=bookService.updateBook(id, updatedBook);
        return ResponseEntity.ok(updateBook);
    }
}
