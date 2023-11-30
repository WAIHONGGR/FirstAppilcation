package com.transaction.product.process.controller;


import com.transaction.product.bin.Merchant;
import com.transaction.product.exception.NotFoundException;
import com.transaction.product.process.service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "api/merchant/")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("/addMerchant")
    public ResponseEntity<String> addMerchant(@Valid @RequestBody Merchant merchant) {
        try {
            Merchant savedMerchant = merchantService.saveMerchant(merchant);

            System.out.println("\n\n\n\n\n\n\n\n" +
                    "The new Merchant info has been received." +
                    "\nMerchant info\n" + "================="
                    + savedMerchant.toString()); // print for debugging

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("The new Merchant info has been posted."); // output for postman client side
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add Merchant. Error: " + e.getMessage()); // handle errors
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMerchant(@PathVariable Long id) {
        Optional<Merchant> merchant = merchantService.findById(id);

        if (merchant.isPresent()) {
            return ResponseEntity.ok(merchant.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Merchant not found");
        }
    }


    @DeleteMapping(path = "/deleteMerchant/{id}")
    public String deleteMerchant(@PathVariable Long id) {
        try {
            if (merchantService.deleteMerchant(id) != null) {
                return "Merchant with ID " + id + " has been deleted.";
            } else {
                throw new NotFoundException("Merchant id not found");
            }
        } catch (NotFoundException e) {
            return "Merchant with ID " + id + " not found.";
        } catch (Exception e) {
            return "Failed to delete Merchant. Error: " + e.getMessage();
        }
    }



}

