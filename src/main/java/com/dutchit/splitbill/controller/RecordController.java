package com.dutchit.splitbill.controller;


import com.dutchit.splitbill.dto.RecordRequest;
import com.dutchit.splitbill.model.Record;
import com.dutchit.splitbill.repository.CustomerRepository;
import com.dutchit.splitbill.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


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
//        Record.RecordBuilder recordBuilder = Record.builder();
//        recordBuilder.fromCustomer();
//        recordBuilder.toCustomer(recordRequest.getToCustomer());
//        recordBuilder.amount(recordRequest.getAmount());
//        return recordRepository.save(recordBuilder.build());
        return null;
    }
}
