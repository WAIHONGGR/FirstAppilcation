package com.transaction.product.process.repo;

import com.transaction.product.bin.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //This for database access
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
    Merchant findById(int id);
}
