package com.transaction.product.process.controller;


import com.transaction.product.bin.Transaction;
import com.transaction.product.exception.NotFoundException;
import com.transaction.product.process.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/addTransaction")
    public String addTransaction(@Valid @RequestBody Transaction transaction) {
        try {

            Transaction savedTransaction = transactionService.saveTransaction(transaction);

            System.out.println("\n\n\n\nThe new Transaction info has been received.");

            System.out.println("\n\n\n\nTransaction info\n" + "================="
                    + savedTransaction.toString());

            System.out.printf("Charge : RM %.2f\n", savedTransaction.getCharge());

            return "The new Transaction info has been posted.";

        } catch (DataAccessException e) {
            return "Failed to process the transaction due to a database error.";
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getTransaction(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.findTransactionById(id);

        if (transaction.isPresent()) {
            return ResponseEntity.ok(transaction.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transaciton not found.");
        }
    }

    @DeleteMapping(path = "/deleteTransaction/{id}")
    public String deleteTransaciton(@PathVariable Long id) {
        try {
            if (transactionService.deleteTransaction(id) != null) {
                return "Transaction with ID " + id + " has been deleted.";
            } else {
                throw new NotFoundException("Transaction id not found.");
            }
        } catch (NotFoundException e) {
            return "Transaction with ID " + id + " not found.";
        } catch (Exception e) {
            return "Failed to delete Transaction. Error: " + e.getMessage();
        }
    }



}
