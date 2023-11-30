package com.transaction.product.process.service;

import com.transaction.product.bin.Merchant;
import com.transaction.product.process.repo.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    //insert operation
    public Merchant saveMerchant(Merchant merchant) {
        return merchantRepository.save(merchant); // Save the new merchant to the database
    }

    //select operation based on primary key
    public Optional<Merchant> findById(Long id) {
        return merchantRepository.findById(id);
    }

    public String deleteMerchant(Long mid) {
        boolean exist =  merchantRepository.existsById(mid);
        if (!exist){
            throw new IllegalStateException("Merchant ID not found.");
        }else{
            merchantRepository.deleteById(mid);
            return "Merchant ID " + mid + "has been deleted.";
        }

    }


}


