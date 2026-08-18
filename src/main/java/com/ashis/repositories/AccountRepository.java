package com.ashis.repositories;

import com.ashis.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    boolean existsByCustomerAccountNo(String customerAccountNo);

    Optional<Account> findByCustomerAccountNo(String accountNo);



}
