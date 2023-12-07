package com.transaction.product.process.service;

import com.transaction.product.bin.Merchant;
import com.transaction.product.exception.NotFoundException;
import com.transaction.product.process.repo.MerchantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final TransactionService transactionService;

    //insert operation
    public Merchant saveMerchant(Merchant merchant) {
        return merchantRepository.save(merchant); // Save the new merchant to the database
    }

    //select operation based on primary key
    public Optional<Merchant> findById(Long id) {
        return merchantRepository.findById(id);
    }

    public double calChargeByMid(Optional<Merchant> merchant) {
        return transactionService.calChargeByMid(merchant.get().getId());
    }

    public String deleteMerchant(Long id) {
        boolean exist = merchantRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("Merchant ID not found.");
        } else {
            merchantRepository.deleteById(id);
            return "Merchant ID " + id + "has been deleted.";
        }

    }

    @Transactional
    public String updateMerchant(Long id, Merchant updatedMerchant) {
        Merchant existingMerchant = merchantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Merchant ID not found."));

        if (updatedMerchant.getName() != null && !updatedMerchant.getName().isEmpty()) {
            existingMerchant.setName(updatedMerchant.getName());
        }

        if (updatedMerchant.getCreditRate() > 0 && existingMerchant.getCreditRate() != updatedMerchant.getCreditRate()) {
            existingMerchant.setCreditRate(updatedMerchant.getCreditRate());
        }

        if (updatedMerchant.getDebitRate() > 0 && existingMerchant.getDebitRate() != updatedMerchant.getDebitRate()) {
            existingMerchant.setDebitRate(updatedMerchant.getDebitRate());
        }

        return "Merchant ID: " + id + " updated successfully.";

    }


}


