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

    public User getSender() {
        return this.sender;
    }

    public List<User> getReceivers() {
        return this.receivers;
    }

    public int getTime() {
        return this.time;
    }

    public int getPriority() {
        return this.msg_priority;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getContent() {
        return this.content;
    }

    public List<String> getAttachments() {
        return this.attachments;
    }
}



