package com.MailServer.CipherPost.Commands.Messages;

import java.util.List;

import com.MailServer.CipherPost.entities.Message;

public class SearchMessages implements MessageCommand<List<Message>>{
    private String criteria;
    private String search_input;

    //!! private MessageFacade msg_facade;
    public List<Message> execute()
    {
        return null; //will be removed
        //!!return msg_facade.searchBy(criteria,searchinput);
    }
    
}
