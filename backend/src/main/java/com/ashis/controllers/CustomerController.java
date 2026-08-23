package com.ashis.controllers;

import com.ashis.dto.*;
import com.ashis.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banking")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;



    @PostMapping("/credit")
    public TransactionResponseDto depositeAmount(@RequestBody TransactionDto transactionDto){

       return customerService.depositeAmount(transactionDto);


    }

    @PostMapping("/debit")
    public TransactionResponseDto withdrawAmount(@RequestBody TransactionDto transactionDto){
        return customerService.withdrawAmount(transactionDto);

    }

    @GetMapping("/check")
    public AccountBalanceRespnse checkBalance(@RequestBody AccountDto accountDto){
        return  customerService.checkBalance(accountDto);
    }

    @PostMapping("/statement")
    public List<StatementDto> checkStatement(@RequestBody TransactionDto transactionDto){
        return customerService.checkStatement(transactionDto);
    }

    @PostMapping("/transfer")
    public TransferResponseDto transferBalance(@RequestBody TransferBalanceDto transferBalanceDto){
        return customerService.transferBalance(transferBalanceDto);

    }


}
