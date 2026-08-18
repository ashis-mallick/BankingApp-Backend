package com.ashis.services;

import com.ashis.dto.TransactionDto;
import com.ashis.dto.TransactionResponseDto;
import com.ashis.entities.Account;
import com.ashis.entities.Transactions;
import com.ashis.repositories.AccountRepository;
import com.ashis.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionResponseDto depositeAmount(TransactionDto transactionDto){


        //find customer by account no exist or not
        Account account = accountRepository.findByCustomerAccountNo
                        (transactionDto.getCustomerAccountNo()).
                orElseThrow(() -> new RuntimeException("Account not Exist"));

        List<Transactions> transactions = transactionRepository.findByCustomer(account.getCustomer());

        Transactions deposite = new Transactions();

        if(transactions.isEmpty()){
            deposite.setAmount(transactionDto.getAmount());
            account.setTotalAmount(transactionDto.getAmount()); //added to account
            deposite.setDescription("INITIAL DEPOSIT");
            deposite.setStatus("SUCCESS");

            deposite.setTransactionId(generateTransationId());
            deposite.setCustomer(account.getCustomer());
            deposite.setTransactionType(transactionDto.getTransactionType());

            Transactions savedCreditTransaction = transactionRepository.save(deposite);
            accountRepository.save(account);
            return new TransactionResponseDto(
                    account.getCustomerAccountNo()
                    ,savedCreditTransaction.getCustomer().getCustomerFirstName()+" "+savedCreditTransaction.getCustomer().getCustomerLastName(),
                    savedCreditTransaction.getTransactionId(),
                    "Amount Credited Successfully");

        } else{

            deposite.setAmount(transactionDto.getAmount());
            deposite.setDescription(transactionDto.getTransactionDescription());
            deposite.setTransactionType(transactionDto.getTransactionType());
            deposite.setTransactionId(generateTransationId());
            deposite.setCustomer(account.getCustomer());
            deposite.setStatus("SUCCESS");


            Transactions savedCreditTransaction= transactionRepository.save(deposite);
            account.setTotalAmount(account.getTotalAmount().add(transactionDto.getAmount()));
            accountRepository.save(account);

            return new TransactionResponseDto(
                    account.getCustomerAccountNo()
                    ,savedCreditTransaction.getCustomer().getCustomerFirstName()+" "+savedCreditTransaction.getCustomer().getCustomerLastName(),
                    savedCreditTransaction.getTransactionId(),
                    "Amount Credited Successfully");
        }


    }

    private String generateTransationId() {

        String time = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss"));
        return "TXN"+time+ UUID.randomUUID();
    }

    public TransactionResponseDto withdrawAmount(TransactionDto transactionDto) {

        //find if customer exist or not
        Account account = accountRepository.findByCustomerAccountNo
                (transactionDto.getCustomerAccountNo()).
                orElseThrow(() -> new RuntimeException("Account not Exist"));


        List<Transactions> transactions = transactionRepository.findByCustomer(account.getCustomer());

        Transactions withdrawal = new Transactions();

        if(account.getTotalAmount().compareTo(transactionDto.getAmount())<0){
            withdrawal.setCustomer(account.getCustomer());
            withdrawal.setAmount(transactionDto.getAmount());
            withdrawal.setTransactionId(generateTransationId());
            withdrawal.setTransactionType(transactionDto.getTransactionType());
            withdrawal.setDescription("Withdrawal");
            withdrawal.setStatus("FAILED");

            Transactions savedDebitTransaction=transactionRepository.save(withdrawal);
            account.setTotalAmount(account.getTotalAmount().subtract(transactionDto.getAmount()));


            return new TransactionResponseDto(
                    transactionDto.getCustomerAccountNo()
                    ,savedDebitTransaction.getCustomer().getCustomerFirstName()+" "+savedDebitTransaction.getCustomer().getCustomerLastName(),
                    savedDebitTransaction.getTransactionId(),
                    "Amount Withdrawal Failed");



        }else {
            withdrawal.setCustomer(account.getCustomer());
            withdrawal.setAmount(transactionDto.getAmount());
            withdrawal.setTransactionId(generateTransationId());
            withdrawal.setTransactionType(transactionDto.getTransactionType());
            withdrawal.setDescription("Withdrawal");
            withdrawal.setStatus("SUCCESS");

            Transactions savedDebitTransaction=transactionRepository.save(withdrawal);
            account.setTotalAmount(account.getTotalAmount().subtract(transactionDto.getAmount()));
            accountRepository.save(account);

            return new TransactionResponseDto(
                    transactionDto.getCustomerAccountNo()
                    ,savedDebitTransaction.getCustomer().getCustomerFirstName()+" "+savedDebitTransaction.getCustomer().getCustomerLastName(),
                    savedDebitTransaction.getTransactionId(),
                    "Amount Withdrawal Successful");

        }


    }
}
