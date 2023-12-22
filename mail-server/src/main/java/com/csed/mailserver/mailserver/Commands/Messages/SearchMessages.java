package com.csed.mailserver.mailserver.Commands.Messages;

import java.util.List;

import com.csed.mailserver.mailserver.Clients.Message;

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
