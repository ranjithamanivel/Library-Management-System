package com.example.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Library.entity.BorrowingTransaction;
import java.util.List;

public interface BorrowingRepository extends JpaRepository<BorrowingTransaction, Long> {
    List<BorrowingTransaction> findByUserId(Long userId);
}
