package com.MailServer.CipherPost.Services;

import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.User;
import com.MailServer.CipherPost.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Contact getContactById(long contactId) {
        return contactRepository.findById(contactId).orElse(null);
    }

    public List<Contact> getContactByUserId(Long userId) {
        return contactRepository.findByUserId(userId);
    }
    public List<Contact> getSortedContactsByUserId(Long userId) {
        return contactRepository.findByUserIdOrderByNickname(userId);
    }

    public  List<Contact> getContactByUserIdAndNicknameContainingIgnoreCase(Long userId, String nickname) {
        return contactRepository.findByUserIdAndNicknameContainingIgnoreCase(userId, nickname);
    }
//    public Contact getContactByNicknameContaining(String nickname) {
//        return contactRepository.findByNicknameContaining(nickname);
//    }
}
