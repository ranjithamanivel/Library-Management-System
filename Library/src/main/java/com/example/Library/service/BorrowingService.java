package com.example.Library.service;
import org.springframework.stereotype.Service;
import com.example.Library.repository.*;
import com.example.Library.entity.*;
import java.util.*;
import java.time.*;

@Service
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BorrowingService(BorrowingRepository borrowingRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.borrowingRepository = borrowingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public BorrowingTransaction borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No available copies of the book");
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowingTransaction transaction = new BorrowingTransaction();
        transaction.setUser(user);
        transaction.setBook(book);
        transaction.setBorrowDate(LocalDate.now());
        transaction.setDueDate(LocalDate.now().plusWeeks(2));
        return borrowingRepository.save(transaction);
    }

    public BorrowingTransaction returnBook(Long transactionId) {
        BorrowingTransaction transaction = borrowingRepository.findById(transactionId).orElseThrow(() -> new RuntimeException("Transaction not found"));
        Book book = transaction.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        transaction.setReturnDate(LocalDate.now());
        return borrowingRepository.save(transaction);
    }
    public List<BorrowingTransaction> getAllTransactions(Long userId) {
        return borrowingRepository.findByUserId(userId);
    }

}
