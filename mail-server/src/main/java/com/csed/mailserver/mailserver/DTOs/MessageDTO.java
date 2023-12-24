package com.csed.mailserver.mailserver.DTOs;

import java.util.*;
import com.csed.mailserver.mailserver.Clients.User;

public class MessageDTO {
    private User sender;
    private List<User> receivers;
    private int time;
    private int msg_priority;
    private String subject;
    private String content;
    private List<String> attachments;

    private MessageDTO(MessageDTOBuilder builder)
    {
        this.sender = builder.sender;
        this.receivers = builder.receivers;
        this.time = builder.time;
        this.msg_priority = builder.msg_priority;
        this.subject = builder.subject;
        this.content = builder.content;
        this.attachments = builder.attachments;


    }

    public static class MessageDTOBuilder{
        private User sender;
        private List<User> receivers;
        private int time;
        private int msg_priority;
        private String subject;
        private String content;
        private List<String> attachments;

        public MessageDTOBuilder(User sender,List<User> receivers,int time,int msg_priority,String subject,String content)
        {
            this.sender = sender;
            this.receivers = receivers;
            this.time=time;
            this.msg_priority=msg_priority;
            this.subject=subject;
            this.content=content;
        }

         public MessageDTOBuilder attachements(List<String> attachemnts)
         {
            this.attachments = attachemnts;
            return this;    
         }

         public MessageDTO build()
         {
            return new MessageDTO(this);
         }

    }

    public User getSender()
    {
        return this.sender;
    }
    public List<User> getReceivers()
    {
        return this.receivers;
    }
    public int getTime()
    {
        return this.time;
    }
    public int getPriority()
    {
        return this.msg_priority;
    }
    public String getSubject()
    {
        return this.subject;
    }
    public String getContent()
    {
        return this.content;
    }
    public List<String> getAttachments()
    {
        if (this.attachments == null)
            this.attachments = new ArrayList<>();
        return this.attachments;
    }    




}



