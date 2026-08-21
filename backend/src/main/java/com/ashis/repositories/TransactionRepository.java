package com.ashis.repositories;

import com.ashis.entities.Customer;
import com.ashis.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions,String> {

    List<Transactions> findByCustomer(Customer customer);

}
