package com.csed.mailserver.mailserver.Clients;
import com.csed.mailserver.mailserver.DTOs.MessageDTO;

public class Message {
    private User sender;
    private User receiver;
    private int time;
    private int msg_priority;
    private String subject;
    private String content;
    private String [] attachments;

    public Message(MessageDTO message)
    {
        this.sender = message.sender;
        this.receiver = message.receiver;
        this.subject= message.subject;
        this.content = message.content;
        this.time = message.time;
        this.msg_priority = message.msg_priority;
        this.attachments = message.attachments;
    }
}
