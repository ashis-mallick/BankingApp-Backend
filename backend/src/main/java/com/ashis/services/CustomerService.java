package com.ashis.services;

import com.ashis.dto.*;
import com.ashis.entities.Account;
import com.ashis.entities.Transactions;
import com.ashis.repositories.AccountRepository;
import com.ashis.repositories.TransactionRepository;
import com.ashis.utils.TransactionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {

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
            deposite.setTransactionType(TransactionType.DEPOSIT);

            Transactions savedCreditTransaction = transactionRepository.save(deposite);
            accountRepository.save(account);
            return new TransactionResponseDto(
                    account.getCustomerAccountNo()
                    ,savedCreditTransaction.getCustomer().getCustomerFirstName()+" "+savedCreditTransaction.getCustomer().getCustomerLastName(),
                    savedCreditTransaction.getTransactionId(),
                    "Amount Credited Successfully",
                    account.getTotalAmount());

        } else{

            deposite.setAmount(transactionDto.getAmount());
            deposite.setDescription(transactionDto.getTransactionDescription());
            deposite.setTransactionType(TransactionType.DEPOSIT);
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
                    "Amount Credited Successfully",
                    account.getTotalAmount());
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
            withdrawal.setTransactionId(generateTransationId());
            withdrawal.setTransactionType(TransactionType.WITHDRAW);
            withdrawal.setDescription("Withdrawal");
            withdrawal.setStatus("FAILED");

            Transactions savedDebitTransaction=transactionRepository.save(withdrawal);
//            account.setTotalAmount(account.getTotalAmount().subtract(transactionDto.getAmount()));


            return new TransactionResponseDto(
                    transactionDto.getCustomerAccountNo()
                    ,savedDebitTransaction.getCustomer().getCustomerFirstName()+" "+savedDebitTransaction.getCustomer().getCustomerLastName(),
                    savedDebitTransaction.getTransactionId(),
                    "Amount Withdrawal Failed",
                    account.getTotalAmount());



        }else {
            withdrawal.setCustomer(account.getCustomer());
            withdrawal.setAmount(transactionDto.getAmount());
            withdrawal.setTransactionId(generateTransationId());
            withdrawal.setTransactionType(TransactionType.WITHDRAW);
            withdrawal.setDescription("Withdrawal");
            withdrawal.setStatus("SUCCESS");

            Transactions savedDebitTransaction=transactionRepository.save(withdrawal);
            account.setTotalAmount(account.getTotalAmount().subtract(transactionDto.getAmount()));
            accountRepository.save(account);

            return new TransactionResponseDto(
                    transactionDto.getCustomerAccountNo()
                    ,savedDebitTransaction.getCustomer().getCustomerFirstName()+" "+savedDebitTransaction.getCustomer().getCustomerLastName(),
                    savedDebitTransaction.getTransactionId(),
                    "Amount Withdrawal Successful",
                    account.getTotalAmount());

        }


    }

    public AccountBalanceRespnse checkBalance(AccountDto accountDto) {


        Account account = accountRepository.findByCustomerAccountNo
                        (accountDto.getCustomerAccountNo())
                .orElseThrow(() -> new RuntimeException("Account not exist"));


        return new AccountBalanceRespnse(accountDto.getCustomerAccountNo(),account.getTotalAmount(), LocalDate.now());
    }

    public List<StatementDto> checkStatement(TransactionDto transactionDto) {
        Account account = accountRepository.findByCustomerAccountNo
                        (transactionDto.getCustomerAccountNo())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        List<Transactions> transactions = transactionRepository.findByCustomer(account.getCustomer());

        List<StatementDto> statementList = transactions.stream().map(t ->
        {
            StatementDto stmt = new StatementDto();
            stmt.setCustomerId(t.getCustomer().getCustomerId());
            stmt.setCustomerTransactionId(t.getTransactionId());
            stmt.setAmount(t.getAmount());
            stmt.setTransactionDescription(t.getDescription());
            stmt.setTransactionType(TransactionType.CHECK);
            stmt.setTransactionStatus(t.getStatus());

            return stmt;

        }).toList();

        return statementList;
    }


    public TransferResponseDto transferBalance(TransferBalanceDto transferBalanceDto){

        Account senderAccount = accountRepository.findByCustomerAccountNo
                (transferBalanceDto.getSenderAccountNo()).orElseThrow(() ->
                new RuntimeException("Sender doesn't exist"));
        Account receiverAccount = accountRepository.findByCustomerAccountNo
                (transferBalanceDto.getReceiverAccountNo()).orElseThrow(() ->
                new RuntimeException("Receiver doesn't exist"));

        if(senderAccount.getTotalAmount().compareTo(transferBalanceDto.getAmount())>=0){
            Transactions sendAmount= new Transactions();

            sendAmount.setAmount(transferBalanceDto.getAmount());
            sendAmount.setCustomer(receiverAccount.getCustomer());
            sendAmount.setTransactionId(generateTransationId());
            sendAmount.setDescription(transferBalanceDto.getDescription());
            sendAmount.setStatus("SUCCESS");
            sendAmount.setTransactionType(TransactionType.TRANSFER);


            Transactions sent = transactionRepository.save(sendAmount);

            // added amount to receiver account
            receiverAccount.setTotalAmount(receiverAccount.getTotalAmount().add(transferBalanceDto.getAmount()));
            accountRepository.save(receiverAccount);

            // deduct amount from sender account
            senderAccount.setTotalAmount(senderAccount.getTotalAmount().subtract(transferBalanceDto.getAmount()));
            accountRepository.save(senderAccount);


            return new TransferResponseDto(
                    transferBalanceDto.getSenderAccountNo(),
                    transferBalanceDto.getReceiverAccountNo(),
                    transferBalanceDto.getAmount(),
                    transferBalanceDto.getDescription(),
                    sent.getStatus(),
                    LocalDate.now()

            );






        }else {
            Transactions sendAmount= new Transactions();

            sendAmount.setAmount(transferBalanceDto.getAmount());
            sendAmount.setCustomer(receiverAccount.getCustomer());
            sendAmount.setTransactionId(generateTransationId());
            sendAmount.setDescription(transferBalanceDto.getDescription());
            sendAmount.setStatus("FAILED");
            sendAmount.setTransactionType(TransactionType.TRANSFER);

            Transactions sent = transactionRepository.save(sendAmount);

            return new TransferResponseDto(
                    transferBalanceDto.getSenderAccountNo(),
                    transferBalanceDto.getReceiverAccountNo(),
                    transferBalanceDto.getAmount(),
                    transferBalanceDto.getDescription(),
                    sent.getStatus(),
                    LocalDate.now()

            );




        }




    }




}
