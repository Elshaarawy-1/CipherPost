
package com.MailServer.CipherPost.Commands.Messages;

import com.MailServer.CipherPost.Facades.MessageFacade;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.Message;

public class MoveMessage implements MessageCommand<Void> {


    private final MessageFacade messageFacade;
    private final Message message;
    private final Folder old_folder;
    private final Folder new_folder;


    public MoveMessage(MessageFacade messageFacade, Message message, Folder old_folder, Folder new_folder) {
        this.messageFacade = messageFacade;
        this.message = message;
        this.old_folder = old_folder;
        this.new_folder = new_folder;
    }

    public Void execute()
    {
        messageFacade.moveMessageToFolder(message, old_folder, new_folder);
        return null;
    }
}
