package com.PB.PhoneBook.Controller;

import com.PB.PhoneBook.Entity.Customer;
import com.PB.PhoneBook.Service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/PhoneBook")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/createCustomer")
    ResponseEntity<Optional<Customer>> createCustomer(@RequestBody Customer customer){
        Optional<Customer> newCustomer = customerService.createCustomer(customer);
        if(newCustomer.isPresent()){
            return ResponseEntity.ok(newCustomer);
        }
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @DeleteMapping("/deleteCustomer/{name}")
    ResponseEntity<Optional<Customer>> deleteCustomer(@PathVariable String name){
        Optional<Customer> deleteCustomer = customerService.customerToDelete(name);
        if(deleteCustomer.isEmpty()){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        customerService.removeCustomer(deleteCustomer.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<Customer> getAllCust(){
        return customerService.customersList();
    }

}
