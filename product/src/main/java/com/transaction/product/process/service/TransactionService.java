package com.transaction.product.process.service;

import com.transaction.product.bin.Merchant;
import com.transaction.product.bin.Transaction;
import com.transaction.product.exception.NotFoundException;
import com.transaction.product.process.repo.MerchantRepository;
import com.transaction.product.process.repo.TransactionRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final MerchantRepository merchantRepository;
    private final EntityManager entityManager;

    //insert operation
    public Transaction saveTransaction(Transaction transaction) {

        Merchant merchant = entityManager.find(Merchant.class, transaction.getMerchant().getId());

        if (merchant != null) {
            if (Character.toUpperCase(transaction.getCardType()) == 'C'
                    | Character.toUpperCase(transaction.getCardType()) == 'D') {
                transaction.setCardType(Character.toUpperCase(transaction.getCardType()));
                transaction.setCharge(performTransactionCal(transaction));//perform calculation
                return transactionRepository.save(transaction);
            }else {
                throw new IllegalStateException
                        ("Card type should be either C = Credit Card or D = Debit Card.");
            }
        } else {
            throw new NotFoundException
                    ("Merchant with ID " + transaction.getMerchant().getId() + " does not exist!");
        }
    }

    //Calculation charge
    public double performTransactionCal(Transaction transaction){
        Optional<Merchant> merchant = merchantRepository.findById(transaction.getMerchant().getId());

        if (merchant.isPresent()) {

            return transaction.getAmount() * ((Character.toUpperCase(transaction.getCardType()) == 'C') ?
                    merchant.get().getCreditRate() : merchant.get().getDebitRate()) / 100;

        } else {
            throw new NotFoundException("Merchant with ID "
                    + transaction.getMerchant().getId() + " does not exist!");
        }
    }

    //select operation based on primary key
    public Optional<Transaction> findTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public String deleteTransaction(Long id) {
        boolean exist =  transactionRepository.existsById(id);
        if (!exist){
            throw new IllegalStateException("Transaciton ID not found.");
        }else{
            transactionRepository.deleteById(id);
            return "Transaciton ID " + id + "has been deleted.";
        }

    }


    public double calChargeByMid(Long mid) {
        List<Transaction> transactions = transactionRepository.findTotalChargeByMid(mid);

        return transactions.stream()
                .mapToDouble(Transaction::getCharge)
                .sum();
    }



}



