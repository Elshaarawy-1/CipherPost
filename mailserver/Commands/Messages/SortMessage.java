package com.csed.mailserver.mailserver.Commands.Messages;

import java.util.List;

import com.csed.mailserver.mailserver.Clients.Message;

public class SortMessage implements MessageCommand<List<Message>>{
    private String criteria;
   //!! private MessageFacade msg_facade;

    
    //!! private MessageFacade msg_facade;
    public List<Message> execute()
    {
        return null;
        //!!return msg_facade.sortBy(criteria);
    }
}
