package com.ashis.services;

import com.ashis.dto.RegisterDto;
import com.ashis.dto.RegisterResponseDto;
import com.ashis.entities.Account;
import com.ashis.entities.Customer;
import com.ashis.entities.User;
import com.ashis.repositories.AccountRepository;
import com.ashis.repositories.RegisterRepository;
import com.ashis.repositories.UserRepository;
import com.ashis.utils.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RegisterRepository registerRepository;
    private final AccountRepository accountRepository;
    private  final UserRepository userRepository;


    public RegisterResponseDto saveRegisterData(RegisterDto registerDto){

        Customer customer = new Customer();

        customer.setCustomerAddress(registerDto.getCustomerAddress());
        customer.setCustomerEmail(registerDto.getCustomerEmail());
        customer.setCustomerPhone(registerDto.getCustomerPhone());
        customer.setCustomerFirstName(registerDto.getCustomerFirstName());
        customer.setCustomerLastName(registerDto.getCustomerLastName());
        customer.setCustomerDateOfBirth(registerDto.getCustomerDateOfBirth());
        customer.setCustomerCreatedAt(LocalDateTime.now());


        Customer savedCustomer = registerRepository.save(customer);


        String accountNo;

        do{
            accountNo = generateAccountNo();

        } while (accountRepository.existsByCustomerAccountNo(accountNo));

        Account account = new Account();
        account.setCustomer(customer);
        account.setCustomerAccountNo(accountNo);
        account.setCustomerCreatedAt(LocalDateTime.now());
        account.setTotalAmount(BigDecimal.ZERO);

        Account savedCredential = accountRepository.save(account);

      // set user

        User user = new User();

        user.setRole(Roles.CUSTOMER);
        user.setUsername(registerDto.getCustomerFirstName()+" "+registerDto.getCustomerLastName());
        user.setCreatedAt(LocalDateTime.now());
        user.setCustomer(savedCredential.getCustomer());


        User savedUser = userRepository.save(user);



        return new RegisterResponseDto(
                savedCustomer.getCustomerFirstName()
                ,savedCustomer.getCustomerLastName()
                ,savedCredential.getCustomerAccountNo(),
                savedCustomer.getCustomerId(),
                "Registration Successfull",
                savedUser.getUserId()
                );



        
    }

    //generate account no
    private String generateAccountNo(){
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000);

        return "1013010" + randomNumber;
    }



}
