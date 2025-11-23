package com.example.Library.service;
import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.AuthorRepository;
import com.example.Library.entity.Author;

import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository,AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository=authorRepository;
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }
//    public Book addBook(Book book) {
//        return bookRepository.save(book);
//    }

    public Book addBook(Book book) {
        Author author = book.getAuthor();

        if (author == null) {
            throw new RuntimeException("Author information is required");
        }

        // Case 1: Author already exists (author.id given)
        if (author.getId() != null) {
            author = authorRepository.findById(author.getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
        }
        // Case 2: New author (only name provided)
        else {
            author =authorRepository.save(author);
        }

        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    public Book addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBookById(bookId);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setTotalCopies(updatedBook.getTotalCopies());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());
        return bookRepository.save(existingBook);
    }
}
