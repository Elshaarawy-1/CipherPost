package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.Adapters.ContactAdapter;
import com.MailServer.CipherPost.Commands.Command;
import com.MailServer.CipherPost.Commands.Contacts.*;
import com.MailServer.CipherPost.Commands.Messages.ComposeMessage;
import com.MailServer.CipherPost.Facades.ContactFacade;
import com.MailServer.CipherPost.Services.ContactService;
import com.MailServer.CipherPost.Services.UserService;
import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.DTOs.ContactDTO;
import com.MailServer.CipherPost.DTOs.FolderDTO;
import com.MailServer.CipherPost.DTOs.MessageDTO;
import com.MailServer.CipherPost.entities.User;
import com.MailServer.CipherPost.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    UserService userService;
    @Autowired
    ContactService contactService;
    @Autowired
    ContactFacade contactFacade;
    @GetMapping("/get/{user_id}")
    public List<ContactDTO> getContacts(@PathVariable Long user_id){
        User user = userService.getUserById(user_id);
        Command<List<Contact>> getCommand = new GetContacts(contactFacade, user);
        List<Contact> contacts = getCommand.execute();
        ContactAdapter adapter = new ContactAdapter();
        return adapter.toListDTO(contacts);
    }

    @PostMapping("/add/{user_id}")
    public ResponseEntity<Void> addContact(@RequestBody ContactDTO new_contact, @PathVariable("user_id") Long user_id) {
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
    @PostMapping("/sort/{user_id}")
    public List<ContactDTO> sortContacts(@PathVariable Long user_id){
        User user = userService.getUserById(user_id);
        Command<List<Contact>> sortCommand = new SortContacts(contactFacade, user);
        List<Contact> contacts = sortCommand.execute();
        ContactAdapter adapter = new ContactAdapter();
        return adapter.toListDTO(contacts);
    }
    @GetMapping("/search/{user_id}")
    public List<ContactDTO> searchContacts(@PathVariable Long user_id, @RequestParam String search){
        User user = userService.getUserById(user_id);
        Command<List<Contact>> searchCommand = new SearchContact(contactFacade, user, search);
        List<Contact> contacts = searchCommand.execute();
        ContactAdapter adapter = new ContactAdapter();
        return adapter.toListDTO(contacts);
    }

}
