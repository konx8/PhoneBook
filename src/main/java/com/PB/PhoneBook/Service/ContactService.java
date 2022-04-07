package com.PB.PhoneBook.Service;

import com.PB.PhoneBook.Entity.Contact;
import com.PB.PhoneBook.Repository.ContactRepo;
import com.PB.PhoneBook.Repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ContactService {

    private ContactRepo contactRepo;
    private CustomerRepo customerRepo;

    public boolean checkIfEmailExist(String email, Contact contact){
        return contact.getEmail().equals(email);
    }

    public boolean checkIfPhoneNumberExist(int phoneNumber, Contact contact){
        return contact.getPhoneNumber()==(phoneNumber);
    }

    public boolean checkIfBusinessPhoneNumberExist(int businessPhoneNumber, Contact contact){
        return contact.getPhoneNumber()==(businessPhoneNumber);
    }

    public Optional<Contact> addContact(Contact contact, int id) {
        contact.setCustomer(customerRepo.getById(id));

        List<Contact> contactList = contactRepo.findAll();
        for (Contact c : contactList) {
            if (checkIfEmailExist(contact.getEmail(), c) ||
                    checkIfPhoneNumberExist(contact.getPhoneNumber(), c) ||
                    checkIfBusinessPhoneNumberExist(contact.getBusinessPhoneNumber(), c)) {
                return Optional.empty();
            }
        }
        return Optional.of(contactRepo.save(contact));
    }

    public List<Contact> getAllContacts(int id){
        return contactRepo.getContactsByCustomerId(id);
    }

    public Optional<Contact> getContactByNameAndSurname(String name,String surname){
        return contactRepo.getContactByNameAndSurname(name, surname);
    }

    public void deleteContact(int id){
        contactRepo.deleteById(id);
    }

    public Optional<Contact> editContact(Contact contact, String name, String surname){

        Optional<Contact> originalContact = getContactByNameAndSurname(name, surname);
        if(originalContact.isPresent()) {
            Contact contact1 = contact;
            contact1.setId(originalContact.get().getId());
            contact1.setCustomer(originalContact.get().getCustomer());
            return Optional.of(contactRepo.save(contact1));
        }
        return Optional.empty();
    }


}
