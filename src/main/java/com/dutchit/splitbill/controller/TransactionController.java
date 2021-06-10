package com.dutchit.splitbill.controller;

import com.dutchit.splitbill.dto.TransactionRequest;
import com.dutchit.splitbill.exception.TransactionNotFoundException;
import com.dutchit.splitbill.model.Customer;
import com.dutchit.splitbill.model.Transaction;
import com.dutchit.splitbill.repository.CustomerRepository;
import com.dutchit.splitbill.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(){

        return transactionRepository.findAll();
    }
    @GetMapping("transactions/{transactionId}")
    public Transaction getTransactionById(@PathVariable (name ="transactionId") Long id){
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if(optionalTransaction.isEmpty()){
            throw new TransactionNotFoundException("transaction not found by id " + id);
        }
        return optionalTransaction.get();
    }
    @PostMapping("/transactions")
    public Transaction addTransaction(@Valid @RequestBody TransactionRequest transactionRequest){
        Optional<Customer> optionalToCustomer = customerRepository.findById(transactionRequest.getToCustomer());
        if(optionalToCustomer.isEmpty()){
            throw new TransactionNotFoundException("to customer not found for id " + transactionRequest.getToCustomer());
        }
        Optional<Customer> optionalFromCustomer = customerRepository.findById(transactionRequest.getFromCustomer());
        if(optionalFromCustomer.isEmpty()){
            throw new TransactionNotFoundException("from customer not found for id " + transactionRequest.getToCustomer());
        }
        Transaction.TransactionBuilder transactionBuilder = Transaction.builder();
        transactionBuilder.fromCustomer(optionalFromCustomer.get());
        transactionBuilder.toCustomer(optionalToCustomer.get());
        transactionBuilder.amount(transactionRequest.getAmount());
        return transactionRepository.save(transactionBuilder.build());
    }
}
