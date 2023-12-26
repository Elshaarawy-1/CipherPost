package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.DTOs.ContactDTO;
import com.MailServer.CipherPost.DTOs.FolderDTO;
import com.MailServer.CipherPost.DTOs.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*/api/contacts" )
public class ContactController {
    @PostMapping("/add")
    public ResponseEntity<Void> addContact(@RequestBody ContactDTO new_contact) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/delete")
    public ResponseEntity<Void> deleteContact(@PathVariable int contact_id) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/sort")
    public List<MessageDTO> sortContacts(@RequestBody FolderDTO current_folder, @RequestBody String criteria) {
        return null;
    }
    @PostMapping("/search")
    public List<MessageDTO> searchContacts(@RequestBody FolderDTO current_folder, @RequestBody String search_input) {
        return null;
    }
    @PostMapping("/edit")
    public ResponseEntity<Void> editContact(@RequestBody ContactDTO updated_contact) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
