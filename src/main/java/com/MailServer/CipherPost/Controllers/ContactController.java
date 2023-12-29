package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.Adapters.ContactAdapter;
import com.MailServer.CipherPost.Commands.Command;
import com.MailServer.CipherPost.Commands.Contacts.*;
import com.MailServer.CipherPost.Facades.ContactFacade;
import com.MailServer.CipherPost.Services.ContactService;
import com.MailServer.CipherPost.Services.UserService;
import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.DTOs.ContactDTO;
import com.MailServer.CipherPost.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin("*")
public class ContactController {
    @Autowired
    UserService userService;
    @Autowired
    ContactService contactService;
    @Autowired
    ContactFacade contactFacade;

    private Long extractUserId(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return Long.parseLong(authorizationHeader.substring(7));
        }
        throw new IllegalArgumentException("Invalid Authorization header format");
    }

    @GetMapping("/get")
    public List<ContactDTO> getContacts(@RequestHeader("Authorization") String authorizationHeader){
        Long user_id = extractUserId(authorizationHeader);
        User user = userService.getUserById(user_id);
        Command<List<Contact>> getCommand = new GetContacts(contactFacade, user);
        List<Contact> contacts = getCommand.execute();
        ContactAdapter adapter = new ContactAdapter();
        return adapter.toListDTO(contacts);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addContact(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ContactDTO new_contact) {
        Long user_id = extractUserId(authorizationHeader);
        User user = userService.getUserById(user_id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            Contact contact = new Contact(user, new_contact);
            Command<Void> addCommand = new AddContact(contactFacade, contact, user);
            addCommand.execute();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/delete/{contact_id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long contact_id) {
        Contact contact = contactService.getContactById(contact_id);
        if (contact == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            Command<Void> deleteCommand = new DeleteContact(contactFacade, contact);
            deleteCommand.execute();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/edit/{contact_id}")
    public ResponseEntity<Void> editContact(@PathVariable Long contact_id, @RequestBody ContactDTO updated_contact) {
        Contact contact = contactService.getContactById(contact_id);
        if (contact == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            Contact updated = new Contact(contact.getUser(),updated_contact);
            Command<Void> editCommand = new EditContact(contactFacade, contact, updated);
            editCommand.execute();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @PostMapping("/sort")
    public List<ContactDTO> sortContacts(@RequestHeader("Authorization") String authorizationHeader ){
        Long user_id = extractUserId(authorizationHeader);
        User user = userService.getUserById(user_id);
        Command<List<Contact>> sortCommand = new SortContacts(contactFacade, user);
        List<Contact> contacts = sortCommand.execute();
        ContactAdapter adapter = new ContactAdapter();
        return adapter.toListDTO(contacts);
    }
    @GetMapping("/search")
    public List<ContactDTO> searchContacts(@RequestHeader("Authorization") String authorizationHeader, @RequestParam String search){
        Long user_id = extractUserId(authorizationHeader);
        User user = userService.getUserById(user_id);
        Command<List<Contact>> searchCommand = new SearchContact(contactFacade, user, search);
        List<Contact> contacts = searchCommand.execute();
        ContactAdapter adapter = new ContactAdapter();
        return adapter.toListDTO(contacts);
    }

}
