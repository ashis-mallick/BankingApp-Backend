package com.ashis.services;

import com.ashis.dto.CustomerStatusDto;
import com.ashis.entities.Account;
import com.ashis.entities.Customer;
import com.ashis.repositories.AccountRepository;
import com.ashis.repositories.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RegisterRepository registerRepository;
    private final AccountRepository accountRepository;

    public List<Customer> viewAllCustomers() {

        List<Customer> all = registerRepository.findAll();

        return all;


    }

    public Customer searchByAccountNo(String accountNo) {


        return accountRepository.findByCustomerAccountNo(accountNo).get().getCustomer();





    }

    public CustomerStatusDto changeActiveStatus(CustomerStatusDto customerStatusDto) {
        Account byCustomerAccountNo =
                accountRepository.findByCustomerAccountNo(customerStatusDto.getAccountNo()).get();

        byCustomerAccountNo.setStatus(customerStatusDto.getAccountStatus());

        Account savedStatus = accountRepository.save(byCustomerAccountNo);

        customerStatusDto.setAccountStatus(savedStatus.getStatus());

        return customerStatusDto;
    }
}
