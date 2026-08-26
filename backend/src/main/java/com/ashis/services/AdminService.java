package com.ashis.services;

import com.ashis.dto.AccountDeletedDto;
import com.ashis.dto.AccountDto;
import com.ashis.dto.ChangePasswordDto;
import com.ashis.dto.CustomerStatusDto;
import com.ashis.entities.Account;
import com.ashis.entities.Customer;
import com.ashis.entities.Transactions;
import com.ashis.entities.User;
import com.ashis.exceptions.AccountNotFoundException;
import com.ashis.repositories.AccountRepository;
import com.ashis.repositories.RegisterRepository;
import com.ashis.repositories.TransactionRepository;
import com.ashis.repositories.UserRepository;
import com.ashis.utils.AccountStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RegisterRepository registerRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public List<Customer> viewAllCustomers() {

        return registerRepository.findAll();


    }

    public Customer searchByAccountNo(String accountNo) {


        return accountRepository.findByCustomerAccountNo(accountNo)
                .orElseThrow(()->new AccountNotFoundException("Account not Found ")).getCustomer();


    }

    public CustomerStatusDto changeActiveStatus(CustomerStatusDto customerStatusDto) {
        Account byCustomerAccountNo =
                accountRepository.findByCustomerAccountNo(customerStatusDto.getAccountNo())
                        .orElseThrow(()->new AccountNotFoundException("Account not Found "));

        byCustomerAccountNo.setStatus(customerStatusDto.getAccountStatus());

        Account savedStatus = accountRepository.save(byCustomerAccountNo);

        customerStatusDto.setAccountStatus(savedStatus.getStatus());

        return customerStatusDto;
    }

    public ChangePasswordDto changePassword(AccountDto accountDto) {
        User user = accountRepository
                .findByCustomerAccountNo(accountDto
                   .getCustomerAccountNo())
                .orElseThrow(()->new AccountNotFoundException("Account not Found ")).getCustomer().getUser();

        user.setPassword(accountDto.getCustomerPassword());

        User savedPassword = userRepository.save(user);

        ChangePasswordDto changePasswordDto = new ChangePasswordDto();
        changePasswordDto.setAccountNo(accountDto.getCustomerAccountNo());
        changePasswordDto.setNewPassword(savedPassword.getPassword());
        changePasswordDto.setDescription("successfully changed password");

        return changePasswordDto;



    }

    public AccountDeletedDto deleteCustomer(AccountDto accountDto) {

        System.out.println("account no is "+accountDto.getCustomerAccountNo());

        Account account = accountRepository.findByCustomerAccountNo(accountDto.getCustomerAccountNo())
                .orElseThrow(() -> new AccountNotFoundException("Account not Found "));


        account.setStatus(AccountStatus.DELETED);

        Account savedDeleted = accountRepository.save(account);

        AccountDeletedDto accountDeletedDto = new AccountDeletedDto();
        accountDeletedDto.setAccountNo(savedDeleted.getCustomerAccountNo());
        accountDeletedDto.setStatus(savedDeleted.getStatus());
        accountDeletedDto.setMessage("Account Marked Deleted");

        return accountDeletedDto;


    }

    public List<Transactions> viewAllTransactions() {
       return transactionRepository.findAll();


    }
}
