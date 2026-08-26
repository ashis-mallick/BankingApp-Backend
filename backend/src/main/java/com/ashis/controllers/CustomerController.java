package com.ashis.controllers;

import com.ashis.dto.*;
import com.ashis.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banking")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;



    @PostMapping("/credit")
    public TransactionResponseDto depositeAmount( @Valid @RequestBody TransactionDto transactionDto){

       return customerService.depositeAmount(transactionDto);


    }

    @PostMapping("/debit")
    public TransactionResponseDto withdrawAmount(@Valid @RequestBody TransactionDto transactionDto){
        return customerService.withdrawAmount(transactionDto);

    }

    @PostMapping("/check")
    public AccountBalanceRespnse checkBalance( @Valid @RequestBody AccountDto accountDto){
        return  customerService.checkBalance(accountDto);
    }

    @PostMapping("/statement")
    public List<StatementDto> checkStatement(@Valid @RequestBody TransactionDto transactionDto){
        return customerService.checkStatement(transactionDto);
    }

    @PostMapping("/transfer")
    public TransferResponseDto transferBalance( @Valid @RequestBody TransferBalanceDto transferBalanceDto){
        return customerService.transferBalance(transferBalanceDto);

    }


}
