package com.PB.PhoneBook.Security;

import com.PB.PhoneBook.Entity.Customer;
import com.PB.PhoneBook.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepo.getCustomerByLogin(username);

        customer.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return customer.map(CustomerDetails::new).get();
    }
}
