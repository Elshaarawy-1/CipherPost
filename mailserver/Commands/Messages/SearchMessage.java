package com.csed.mailserver.mailserver.Commands.Messages;

import java.util.List;

import com.csed.mailserver.mailserver.Clients.Message;


public class SearchMessage implements MessageCommand<List<Message>>{
    private String criteria;
    private String searchinput;

    //!! private MessageFacade Message_facade;
    public List<Message> execute()
    {
        return null; //will be removed
        //!!return Message_facade.searchBy(criteria,searchinput);
    }
    
}
