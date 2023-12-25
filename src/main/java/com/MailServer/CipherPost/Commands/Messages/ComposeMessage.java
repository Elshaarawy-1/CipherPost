package com.MailServer.CipherPost.Commands.Messages;

import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.Facades.MessageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComposeMessage implements MessageCommand<Void> {
    private final MessageFacade messageFacade;
    private final Message sent_msg;

    @Autowired
    public ComposeMessage(MessageFacade messageFacade, Message sent_msg) {
        this.messageFacade = messageFacade;
        this.sent_msg = sent_msg;
    }

    public Void execute() {
        // compose email method
        messageFacade.sendMessage(sent_msg);
        return null;
    }
}
