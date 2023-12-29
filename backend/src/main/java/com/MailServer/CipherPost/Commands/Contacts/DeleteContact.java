package com.MailServer.CipherPost.Commands.Contacts;

import com.MailServer.CipherPost.Facades.ContactFacade;
import com.MailServer.CipherPost.entities.Contact;
import org.springframework.stereotype.Component;

public class DeleteContact implements ContactCommand<Void>{
    private final ContactFacade contactFacade;
    private final Contact contact;
    public DeleteContact(ContactFacade contactFacade, Contact contact){
        this.contactFacade = contactFacade;
        this.contact = contact;
    }
    public Void execute(){
        contactFacade.deleteContact(contact);
        return null;
    }
}
