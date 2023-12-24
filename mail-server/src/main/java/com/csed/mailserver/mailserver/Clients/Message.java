package com.csed.mailserver.mailserver.Clients;
import java.util.List;

import com.csed.mailserver.mailserver.DTOs.MessageDTO;

public class Message {
    private User sender;
    private List<User> receivers;
    private int time;
    private int msg_priority;
    private String subject;
    private String content;
    private List<String> attachments;

    public Message(MessageDTO message)
    {
        this.sender = message.getSender();
        this.receivers = message.getReceivers();
        this.subject= message.getSubject();
        this.content = message.getContent();
        this.time = message.getTime();
        this.msg_priority = message.getPriority();
        this.attachments = message.getAttachments();
    }
}
