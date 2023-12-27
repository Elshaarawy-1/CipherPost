package com.MailServer.CipherPost.Commands.Messages;

import java.util.List;

import com.MailServer.CipherPost.Facades.MessageFacade;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.Message;

public class SearchMessages implements MessageCommand<List<Message>>{
    private String criteria;
    private String search_input;
    private final Folder message_folder;
    private MessageFacade messageFacade;
    public SearchMessages(MessageFacade messageFacade, Folder folder, String criteria, String search_input, Folder messageFolder)
    {
        this.messageFacade = messageFacade;
        this.message_folder = messageFolder;
        this.criteria = criteria;
        this.search_input = search_input;
    }
    public List<Message> execute()
    {
        messageFacade.searchMessages(message_folder, criteria, search_input);
        return null; //will be removed
        //!!return msg_facade.searchBy(criteria,searchinput);
    }
    
}
