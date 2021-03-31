package com.sellerapp.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerservice;

    @RequestMapping("/customer")
    public List<Customer> getCustomers(){ return customerservice.getCustomer(); }

    @RequestMapping("/customer/{id}")
    public Optional<Customer> getCustomerId(@PathVariable int id){
        return customerservice.getCustomerById(id);
    }

    @PostMapping("/customer")
     public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
        customerservice.addCustomer(customer);
        return ResponseEntity.ok("Customer Added Successfully!");
    }

    @PutMapping("/customer")
    public void updateCustomer(@RequestBody Customer customer){ customerservice.updateCustomer(customer);}

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable int id){ customerservice.deleteCustomer(id);}
}
