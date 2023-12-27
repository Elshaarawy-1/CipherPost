package com.MailServer.CipherPost.Commands.Contacts;

import com.MailServer.CipherPost.Facades.ContactFacade;
import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.User;

import java.util.List;

public class GetContacts implements ContactCommand<List<Contact>>{
    private final ContactFacade contactFacade;
    private final User user;

    public GetContacts(ContactFacade contactFacade, User user) {
        this.contactFacade = contactFacade;
        this.user = user;
    }

    public List<Contact> execute()
    {
        return contactFacade.getContacts(user);


    }
}
