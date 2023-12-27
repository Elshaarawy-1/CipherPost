//package com.csed.mailserver.mailserver.Commands.Contacts;
//
//import java.util.List;
//
//import com.csed.mailserver.mailserver.Clients.Contact;
//
//public class SortContacts implements ContactCommand<List<Contact>>{
//
//    public List<Contact> execute()
//    {
//        return null; //will be removed
//
//    }
//}


package com.MailServer.CipherPost.Commands.Contacts;

import com.MailServer.CipherPost.Facades.ContactFacade;
import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SortContacts implements ContactCommand<List<Contact>>{
    private final ContactFacade contactFacade;
    private final User user;
    public SortContacts(ContactFacade contactFacade, User user){
        this.contactFacade = contactFacade;
        this.user = user;
    }

    public List<Contact> execute(){
        return contactFacade.getSortedContactsByUserId(user.getId());
    }
}
