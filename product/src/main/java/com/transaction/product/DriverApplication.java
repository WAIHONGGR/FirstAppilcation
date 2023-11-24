package com.transaction.product;

import com.transaction.product.bin.Merchant;
import com.transaction.product.process.service.MerchantService;
import com.transaction.product.process.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DriverApplication implements CommandLineRunner {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private TransactionService transactionService;

    public static void main(String[] args) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        SpringApplication.run(DriverApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // //insert operation
        // // (Merchant)
        // merchantService.insertMerchant("Maybank", 1, 0.5)
        // merchantService.insertMerchant("Public bank", 1, 0.5);
        // //(Transaction)
        transactionService.insertTransaction('C', 100, 1);


        //select operation (Merchant)
//        Merchant foundMerchant = merchantService.findById(1); // set 1 as the first record in db
//
//        if (foundMerchant != null) {
//            System.out.println("Merchant found \n " + foundMerchant.toString());
//        } else {
//            System.out.println("Merchant not found.");
//        }
    }
}