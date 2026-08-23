package com.ashis.repositories;

import com.ashis.entities.Account;
import com.ashis.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<Customer,Long> {



}
