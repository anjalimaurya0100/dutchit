package com.dutchit.splitbill.controller;

import com.dutchit.splitbill.exception.CustomerNotFoundException;
import com.dutchit.splitbill.model.Customer;
import com.dutchit.splitbill.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }


    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable (name = "customerId") Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("customer not found by id " + id);
        }
        return optionalCustomer.get();
    }


    @PutMapping("/customers/{customerId}")
    public Customer updateCustomer(@PathVariable (name = "customerId") Long id , @Valid @RequestBody Customer requestCustomer){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("customer not found by id " + id);
        }
        Customer customer = optionalCustomer.get();
        customer.setFirstName(requestCustomer.getFirstName());
        customer.setLastName(requestCustomer.getLastName());
        customer.setEmail(requestCustomer.getEmail());
        customer.setMobileNumber(requestCustomer.getMobileNumber());
        return customerRepository.save(customer);
    }


    @PostMapping("/customers")
    public Customer  addCustomer(@Valid @RequestBody Customer customerRequest){
        return customerRepository.save(customerRequest);
    }
}
