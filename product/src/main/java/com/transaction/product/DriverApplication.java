package com.transaction.product;

import com.transaction.product.process.service.MerchantService;
import com.transaction.product.process.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@RequiredArgsConstructor
@SpringBootApplication
public class DriverApplication implements CommandLineRunner {

    private final MerchantService merchantService;
    private final TransactionService transactionService;

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("\n\nSpringboot Application Starting....");
    }

}
