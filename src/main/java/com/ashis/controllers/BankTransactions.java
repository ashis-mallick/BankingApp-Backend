package com.ashis.controllers;

import com.ashis.dto.TransactionDto;
import com.ashis.dto.TransactionResponseDto;
import com.ashis.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banking")
@AllArgsConstructor
public class BankTransactions {

    private final TransactionService transactionService;



    @PostMapping("/credit")
    public TransactionResponseDto depositeAmount(@RequestBody TransactionDto transactionDto){

       return transactionService.depositeAmount(transactionDto);


    }

    @PostMapping("/debit")
    public TransactionResponseDto withdrawAmount(@RequestBody TransactionDto transactionDto){
        return transactionService.withdrawAmount(transactionDto);

    }


}
