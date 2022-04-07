package com.PB.PhoneBook.Repository;


import com.PB.PhoneBook.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByLogin(String login);

    Optional<Customer>getCustomerByLogin(String login);

    Optional<Customer> deleteById(int id);



}
