package com.transaction.product.bin;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Data // no need to rewrite the boilerplate code
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id //as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto generate the transaction id (increment such as 1,2,3,4)
    @Column(name = "txn_id")
    private Long id;


    @Column(name = "card_type")
    private char cardType;


    @Positive(message = "Amount should be greater than 0.")
    private double amount;

    private double charge;

    @Column(name = "date_time")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER) //use to identify the retrieve method between the relationship
    @JoinColumn(name = "mid") // This refers to the foreign key column in the Transaction table
    private Merchant merchant;

    @Override
    public String toString() {
        return "\n" + LocalDateTime.now()
                + " [Txn ID] : " + id
                + " [Card Type] : " + cardType
                + " [Amount] : " + amount
                + " [Charge] : " + charge
                + " [MID] : " + merchant.getId();
    }
}
