package me.dio.service;

import me.dio.domain.model.Transaction;
import me.dio.domain.model.TransactionDTO;
import me.dio.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception{
        var payer = this.userService.findById(transaction.payerId());
        var payee = this.userService.findById(transaction.payeeId());

        userService.validateUser(payer, transaction.amount());

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.amount());
        newTransaction.setPayer(payer);
        newTransaction.setPayee(payee);
        newTransaction.setTransactionTime(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transaction.amount()));
        payee.setBalance(payee.getBalance().add(transaction.amount()));
//        payer.getAccount().setBalance(payer.getAccount().getBalance().subtract(transaction.amount()));
//        payee.getAccount().setBalance(payee.getAccount().getBalance().add(transaction.amount()));

        this.repository.save(newTransaction);
        this.userService.saveUser(payer);
        this.userService.saveUser(payee);

        return newTransaction;
    }

}
