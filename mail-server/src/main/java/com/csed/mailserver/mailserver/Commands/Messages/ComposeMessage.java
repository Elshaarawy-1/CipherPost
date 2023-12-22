package com.csed.mailserver.mailserver.Commands.Messages;

import com.csed.mailserver.mailserver.Clients.Message;
import com.csed.mailserver.mailserver.Facades.MessageFacade;

public class ComposeMessage implements MessageCommand<Void>{

    private MessageFacade msg_facade;
    private Message sent_msg;
    ComposeMessage(){}
    public ComposeMessage(Message sent_msg){
        this.sent_msg=sent_msg;
        msg_facade=new MessageFacade();
    }
    public Void execute()
    {
        // compose email method
        msg_facade.sendMessage(sent_msg);
        return null;
    }   
}
