package com.MailServer.CipherPost.Facades;

import com.MailServer.CipherPost.Services.ContactService;
import com.MailServer.CipherPost.Services.FolderMessagesService;
import com.MailServer.CipherPost.Services.FolderService;
import com.MailServer.CipherPost.Services.MessageService;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.User;
import com.MailServer.CipherPost.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactFacade {
    @Autowired
    ContactService contactService;
    public void saveContact(User user, Contact contact) {
        contact.setUser(user);
        contactService.saveContact(user, contact);
    }
    private void deleteContact(Contact contact) {
        contactService.deleteContact(contact);
    }
    private void updateContact(Contact contact) {
        contactService.updateContact(contact);
    }
}