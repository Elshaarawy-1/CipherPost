package com.MailServer.CipherPost.Commands.Messages;

import java.util.List;

import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.Facades.MessageFacade;

public class SortMessages implements MessageCommand<List<Message>> {
    private String criteria;
    private Folder current_folder;
    private MessageFacade msg_facade;
    public List<Message> execute()
    {
        return msg_facade.sortMessages(current_folder,criteria);
    }
}
