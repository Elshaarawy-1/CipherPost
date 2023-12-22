package com.csed.mailserver.mailserver.DTOs;

import com.csed.mailserver.mailserver.Clients.User;

public class MessageDTO {
    public User sender;
    public User receiver;
    public int time;
    public int msg_priority;
    public String subject;
    public String content;
    public String [] attachments;
}
