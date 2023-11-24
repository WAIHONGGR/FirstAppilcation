package com.transaction.product.process.service;

import com.transaction.product.bin.Merchant;
import com.transaction.product.process.repo.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public void insertMerchant(String merchantName, double creditRate, double debitRate) {

        // Create a new Merchant object
        Merchant newMerchant = new Merchant();
        newMerchant.setName(merchantName);
        newMerchant.setCreditRate(creditRate);
        newMerchant.setDebitRate(debitRate);

        merchantRepository.save(newMerchant); // Save the new merchant to the database
    }

    public Merchant findById(int id) {
        return merchantRepository.findById(id);
    }
}


