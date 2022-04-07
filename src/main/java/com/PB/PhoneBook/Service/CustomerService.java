package com.PB.PhoneBook.Service;

import com.PB.PhoneBook.Entity.Customer;
import com.PB.PhoneBook.Repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;


    public Optional<Customer> createCustomer(Customer customer){
        if (customerRepo.findByLogin(customer.getLogin()).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(customerRepo.save(customer));
    }

    public Optional<Customer> customerToDelete(String login){
        Optional<Customer> customerToDelete = customerRepo.getCustomerByLogin(login);
        if(customerToDelete.isEmpty()){
            return Optional.empty();
        }
        return customerToDelete;

    }

    public Optional<Customer> removeCustomer(int customerToDelete){
        return customerRepo.deleteById(customerToDelete);
    }

    public List<Customer> customersList(){
        return customerRepo.findAll();
    }


}
