package com.example.Library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
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
}
