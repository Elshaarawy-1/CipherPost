package com.csed.mailserver.mailserver.Facades;

import com.csed.mailserver.mailserver.Clients.Contact;
import com.csed.mailserver.mailserver.Clients.Message;
import com.csed.mailserver.mailserver.DTOs.MessageDTO;
import com.csed.mailserver.mailserver.Repositories.ContactRepository;
import com.csed.mailserver.mailserver.Repositories.MessageRepository;

import java.util.List;

public class ContactFacade {
    private ContactRepository contactRepository;
    public void addContact(Contact new_contact) {

    }
    public void editContact(Contact updated_contact) {

    }
    public void removeContact(Contact contact) {

    }
    public List<Contact> searchContacts(String search_input) {
        return null;
    }
    public List<Contact> sortContacts(String criteria) {
        return null;
    }
}
