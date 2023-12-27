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
        contactService.saveContact(user, contact);
    }
    public void deleteContact(Contact contact) {
        contactService.deleteContact(contact);
    }
    public void updateContact(Contact contact) {
        contactService.updateContact(contact);
    }
    public Contact getContactById(Long contact_id) {
        return contactService.getContactById(contact_id);
    }
    public List<Contact> getContactByUserId(Long user_id) {
        return contactService.getContactByUserId(user_id);
    }
    public List<Contact> getSortedContactsByUserId(Long user_id) {
        return contactService.getSortedContactsByUserId(user_id);
    }
    public List <Contact> getContactByUserIdAndNicknameContainingIgnoreCase(Long user_id, String nickname) {
        return contactService.getContactByUserIdAndNicknameContainingIgnoreCase(user_id, nickname);
    }

    public List<Contact> getContacts(User user) {
        return contactService.getContactByUserId(user.getId());
    }
}