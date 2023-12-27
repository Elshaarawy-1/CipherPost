package com.MailServer.CipherPost.Commands.Contacts;

import com.MailServer.CipherPost.DTOs.ContactDTO;
import com.MailServer.CipherPost.Facades.ContactFacade;
import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.lang.String;

public class SearchContact implements ContactCommand<List<Contact>>{
    private final ContactFacade contactFacade;
    private final User user;

    private final String nickname;

    public SearchContact(ContactFacade contactFacade, User user, String nickname) {
        this.contactFacade = contactFacade;
        this.user = user;
        this.nickname = nickname;
    }

    public List<Contact> execute(){
        return  contactFacade.getContactByUserIdAndNicknameContainingIgnoreCase(user.getId(), nickname);
    }
}
