package com.transaction.product.process.repo;

import com.transaction.product.bin.Merchant;
import com.transaction.product.bin.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //This for database access
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findById(int id);
}
