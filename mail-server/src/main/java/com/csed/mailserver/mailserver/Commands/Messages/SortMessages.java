package com.csed.mailserver.mailserver.Commands.Messages;

import java.util.List;

import com.csed.mailserver.mailserver.Clients.Folder;
import com.csed.mailserver.mailserver.Clients.Message;
import com.csed.mailserver.mailserver.Facades.MessageFacade;

public class SortMessages implements MessageCommand<List<Message>> {
    private String criteria;
    private Folder current_folder;
    private MessageFacade msg_facade;
    public List<Message> execute()
    {
        return msg_facade.sortMessages(current_folder,criteria);
    }
}
