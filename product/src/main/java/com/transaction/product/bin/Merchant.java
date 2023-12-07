package com.transaction.product.bin;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import java.util.List;


@Data
@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid")
    private Long id;

    private String name;

    @Min(value = 0, message = "Credit rate should not be less than 0.")
    @Column(name = "credit_rate")
    private double creditRate;

    @Min(value = 0, message = "Debit rate should not be less than 0.")
    @Column(name = "debit_rate")
    private double debitRate;

    @Override //custom
    public String toString() {
        return "\nMerchant Name : " + name
                + "\nCredit Rate : " + creditRate
                + "\nDebit Rate : " + debitRate;
    }

}
