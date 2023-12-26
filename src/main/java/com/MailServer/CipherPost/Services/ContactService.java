package com.MailServer.CipherPost.Services;

import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.User;
import com.MailServer.CipherPost.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;
    public void saveContact(User user, Contact contact) {
        contact.setUser(user);
        contactRepository.save(contact);
    }
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }
    public void updateContact(Contact contact) {
        contactRepository.save(contact);
    }
}
