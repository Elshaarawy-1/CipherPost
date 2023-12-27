package com.MailServer.CipherPost.Commands.Messages;

import com.MailServer.CipherPost.Facades.MessageFacade;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.Message;

public class DeleteMessage implements MessageCommand<Void> {


    private final MessageFacade messageFacade;
    private final Message delete_msg;
    private final Folder message_folder;


    public DeleteMessage(MessageFacade messageFacade, Message delete_msg, Folder folder) {
        this.messageFacade = messageFacade;
        this.delete_msg = delete_msg;
        this.message_folder = folder;
    }

    public Void execute()
    {
        messageFacade.deleteMessageFromFolder(delete_msg, message_folder);
        return null;
    }
}
