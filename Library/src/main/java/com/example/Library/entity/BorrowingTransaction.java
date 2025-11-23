package com.example.Library.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BorrowingTransaction {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @ManyToOne
    private User user;
   @ManyToOne
    private Book book;

   private LocalDate borrowDate;
   private LocalDate returnDate;
   private LocalDate dueDate;

   public BorrowingTransaction(){}
    public BorrowingTransaction(User user, Book book, LocalDate borrowDate, LocalDate dueDate) {
         this.user = user;
         this.book = book;
         this.borrowDate = borrowDate;
         this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
