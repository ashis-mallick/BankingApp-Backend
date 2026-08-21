package com.ashis.controllers;

import com.ashis.dto.*;
import com.ashis.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/check")
    public AccountBalanceRespnse checkBalance(@RequestBody AccountDto accountDto){
        return  transactionService.checkBalance(accountDto);
    }

    @PostMapping("/statement")
    public List<StatementDto> checkStatement(@RequestBody TransactionDto transactionDto){
        return transactionService.checkStatement(transactionDto);
    }

    @PostMapping("/transfer")
    public TransferResponseDto transferBalance(@RequestBody TransferBalanceDto transferBalanceDto){
        return transactionService.transferBalance(transferBalanceDto);

    }


}
