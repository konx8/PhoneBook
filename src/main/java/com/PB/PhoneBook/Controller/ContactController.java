package com.PB.PhoneBook.Controller;

import com.PB.PhoneBook.Entity.Contact;
import com.PB.PhoneBook.Service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/contacts")
public class ContactController {

    private ContactService contactService;

    @PostMapping("/{id}/addContact")
    ResponseEntity<Optional<Contact>> addContact(@PathVariable int id, @RequestBody Contact contact){
        Optional<Contact> newContact = contactService.addContact(contact, id);
        if(newContact.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(newContact);
    }

    @GetMapping("/{id}/getAllContacts")
    ResponseEntity<List<Contact>> getAllContacts(@PathVariable int id){
        return ResponseEntity.ok(contactService.getAllContacts(id));
    }

    @GetMapping("/chose/{name}/{surname}")
    ResponseEntity<Optional<Contact>> getContactByNameAndSurname(@PathVariable String name, @PathVariable String surname){
        Optional<Contact> contact = contactService.getContactByNameAndSurname(name, surname);
        if(contact.isEmpty()){
            return new ResponseEntity<>(Optional.empty(),HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/delete/{name}/{surname}")
    ResponseEntity<Void> deleteContact(@PathVariable String name,@PathVariable String surname){
        Optional<Contact> contactToDelete = contactService.getContactByNameAndSurname(name,surname);

        if(contactToDelete.isEmpty()){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        contactService.deleteContact(contactToDelete.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/edit/{name}/{surname}")
    ResponseEntity<Contact> editContact(@RequestBody Contact contact, @PathVariable String name, @PathVariable String surname){

        Optional<Contact> contactToEdit = contactService.editContact(contact, name, surname);
        if (contactToEdit.isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

    }






}
