package com.MailServer.CipherPost.Commands.Contacts;

import com.MailServer.CipherPost.Commands.Contacts.ContactCommand;
import com.MailServer.CipherPost.Facades.ContactFacade;
import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddContact implements ContactCommand<Void>{
    private final ContactFacade contactFacade;
    private final Contact contact;
    private final User user;
    public AddContact(ContactFacade contactFacade, Contact contact, User user){
        this.contactFacade = contactFacade;
        this.contact = contact;
        this.user = user;
    }
    public Void execute(){
        contactFacade.saveContact(user, contact);
        //method to addcontact

        return null;

    }
}