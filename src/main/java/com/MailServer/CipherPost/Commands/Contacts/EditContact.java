package com.MailServer.CipherPost.Commands.Contacts;

import com.MailServer.CipherPost.DTOs.ContactDTO;
import com.MailServer.CipherPost.Facades.ContactFacade;
import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.User;
import org.springframework.stereotype.Component;

@Component
public class EditContact implements ContactCommand<Void>{
    private final ContactFacade contactFacade;
    private final Contact contact;
    private final Contact updated_contact;
    public EditContact(ContactFacade contactFacade, Contact contact, Contact updated_contact){
        this.contactFacade = contactFacade;
        this.contact = contact;
        this.updated_contact = updated_contact;
    }
    public Void execute(){
        this.contact.setNickname(updated_contact.getNickname());
        this.contact.setContact_usernames(updated_contact.getContact_usernames());
        contactFacade.updateContact(this.contact);
        return null;
    }
}