package com.transaction.product.bin;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "merchant")
@Entity
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid")
    private Long id;

    private String name;

    @Column(name = "credit_rate")
    private double creditRate;

    @Column(name = "debit_rate")
    private double debitRate;

    @OneToMany(mappedBy = "merchant")
    private List<Transaction> transactions; // Correct the name of the variable to match the mappedBy attribute

    @Override //custom
    public String toString() {
        return "Merchant ID : " + id
                + "\nMerchant Name : " + name
                + "\nCredit Rate :" + creditRate
                + "\nDebit Rate :" + debitRate;
    }

}
