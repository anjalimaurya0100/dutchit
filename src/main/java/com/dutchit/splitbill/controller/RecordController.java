package com.dutchit.splitbill.controller;


import com.dutchit.splitbill.dto.RecordRequest;
import com.dutchit.splitbill.exception.CustomerNotFoundException;
import com.dutchit.splitbill.exception.RecordNotFoundException;
import com.dutchit.splitbill.model.Customer;
import com.dutchit.splitbill.model.Record;
import com.dutchit.splitbill.repository.CustomerRepository;
import com.dutchit.splitbill.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class RecordController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/records")
    public List<Record> getListOfRecordsOfUser() {
        return recordRepository.findAll();
    }

    @PostMapping("/records")
    public Record addRecord(@Valid @RequestBody RecordRequest recordRequest) {
        Optional<Customer> optionalFromCustomer = customerRepository.findById(recordRequest.getFromCustomer());
        if(optionalFromCustomer.isEmpty()){
            throw new CustomerNotFoundException("from customer not found for id " + recordRequest.getFromCustomer());
        }
        Optional<Customer> optionalToCustomer = customerRepository.findById(recordRequest.getFromCustomer());
        if(optionalToCustomer.isEmpty()){
            throw new CustomerNotFoundException("to customer not found for id " + recordRequest.getToCustomer());
        }
        Record.RecordBuilder recordBuilder = Record.builder();
        recordBuilder.fromCustomer(optionalFromCustomer.get());
        recordBuilder.toCustomer(optionalToCustomer.get());
        recordBuilder.amount(recordRequest.getAmount());
        return recordRepository.save(recordBuilder.build());
    }
    @PutMapping("/records/{recordId}")
    public Record updateRecord(@PathVariable (name = "recordId") Long id ,@Valid @RequestBody RecordRequest recordRequest){
        Optional<Record> optionalRecord = recordRepository.findById(id);
        if(optionalRecord.isEmpty()){
            throw new RecordNotFoundException("record not found for id " + optionalRecord.get());
        }
        Optional<Customer> optionalFromCustomer = customerRepository.findById(recordRequest.getFromCustomer());
        if(optionalFromCustomer.isEmpty()){
            throw new CustomerNotFoundException("from customer not found for id " + recordRequest.getFromCustomer());
        }
        Optional<Customer> optionalToCustomer = customerRepository.findById(recordRequest.getFromCustomer());
        if(optionalToCustomer.isEmpty()){
            throw new CustomerNotFoundException("to customer not found for id " + recordRequest.getToCustomer());
        }
        Record record = optionalRecord.get();
        record.setFromCustomer(optionalFromCustomer.get());
        record.setToCustomer(optionalToCustomer.get());
        record.setAmount(recordRequest.getAmount());
        return recordRepository.save(record);
    }
}
