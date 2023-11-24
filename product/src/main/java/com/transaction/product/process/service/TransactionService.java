package com.transaction.product.process.service;

import com.transaction.product.bin.Merchant;
import com.transaction.product.bin.Transaction;
import com.transaction.product.process.repo.TransactionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final EntityManager entityManager; // Inject EntityManager (Jpa API repo)

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, EntityManager entityManager) {
        this.transactionRepository = transactionRepository;
        this.entityManager = entityManager; // Initialize EntityManager
    }

    public void insertTransaction(char cardType, double amount, long mid) {
        // Create a new Transaction object
        Transaction newTransaction = new Transaction();
        newTransaction.setCardType(Character.toUpperCase(cardType));
        newTransaction.setAmount(amount);
        newTransaction.setDateTime(LocalDateTime.now());

        // Retrieve the Merchant object by ID
        Merchant merchant = entityManager.find(Merchant.class, mid);

        if (merchant != null) { // Check if the merchant exists
            newTransaction.setMerchant(merchant); // Associate the Merchant with the Transaction
            transactionRepository.save(newTransaction); // Save the new transaction to the database
        } else {
            System.out.println("Merchant with ID " + mid + " does not exist !");
        }
    }

    public Transaction findById(int id) {
        return transactionRepository.findById(id);
    }
}
