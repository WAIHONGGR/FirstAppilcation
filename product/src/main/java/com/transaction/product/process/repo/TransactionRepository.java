package com.transaction.product.process.repo;

import com.transaction.product.bin.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //This for database access
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findById(Long id);

    @Query("SELECT t FROM Transaction t WHERE t.merchant.id = :mid")
    List<Transaction> findTotalChargeByMid(Long mid);




}
