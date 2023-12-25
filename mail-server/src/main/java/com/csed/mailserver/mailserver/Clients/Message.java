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
    private Message(MessageBuilder builder) {
        this.sender = builder.sender;
        this.receivers = builder.receivers;
        this.time = builder.time;
        this.msg_priority = builder.msg_priority;
        this.subject = builder.subject;
        this.content = builder.content;
        this.attachments = builder.attachments;
    }
    public static class MessageBuilder {
        private User sender;
        private List<User> receivers;
        private int time;
        private int msg_priority;
        private String subject;
        private String content;
        private List<String> attachments;

        public MessageBuilder(MessageDTO msg) {
            this.sender = msg.getSender();
            this.receivers = msg.getReceivers();
            this.time = msg.getTime();
            this.msg_priority = msg.getPriority();
            this.subject = msg.getSubject();
            this.content = msg.getContent();
        }

        public MessageBuilder withAttachments(List<String> attachments) {
            this.attachments = attachments;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<User> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<User> receivers) {
        this.receivers = receivers;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMsg_priority() {
        return msg_priority;
    }

    public void setMsg_priority(int msg_priority) {
        this.msg_priority = msg_priority;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }
}