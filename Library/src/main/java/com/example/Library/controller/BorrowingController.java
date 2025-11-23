package com.example.Library.controller;
import com.example.Library.service.BorrowingService;
import com.example.Library.entity.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.net.URI;
@RestController
@RequestMapping("/api/borrowings")

public class BorrowingController {
    private final BorrowingService borrowingService;
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/borrow/{userId}/{bookId}")
    public ResponseEntity<BorrowingTransaction> borrowBook(@PathVariable Long userId,@PathVariable Long bookId){
        BorrowingTransaction tx=borrowingService.borrowBook(userId, bookId);
        //return ResponseEntity.created(URI.create("/api/borrowings/"+tx.getId())).body(tx);
        return ResponseEntity.ok(tx);
    }
    @PostMapping("/{id}/return") //this id is transcation id
    public ResponseEntity<BorrowingTransaction> returnBook(@PathVariable("id") Long transactionId) {
        BorrowingTransaction tx = borrowingService.returnBook(transactionId);
        return ResponseEntity.ok(tx);
    }
    @PostMapping("/user/{userId}")
    public List<BorrowingTransaction> byUser(@PathVariable Long transcationId){
        return borrowingService.getAllTransactions(transcationId);
    }
    @GetMapping
    public ResponseEntity<List<BorrowingTransaction>> getAllTransactions(@RequestParam Long userId){
        return ResponseEntity.ok(borrowingService.getAllTransactions(userId));
    }

}
