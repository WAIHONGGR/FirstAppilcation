package com.transaction.product.bin;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data// no need to rewrite the boilerplate code
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id //as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto generate the transaction id (increment such as 1,2,3,4)
    @Column (name = "txn_id")
    private Long id;

    @Column(name = "card_type")
    private char cardType;

    private double amount;

    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "mid") // This refers to the foreign key column in the Transaction table
    private Merchant merchant;


}
