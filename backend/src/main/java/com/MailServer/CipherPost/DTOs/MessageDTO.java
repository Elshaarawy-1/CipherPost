package com.MailServer.CipherPost.DTOs;

import java.sql.Timestamp;
import java.util.*;

import com.MailServer.CipherPost.entities.Attachment;
import com.MailServer.CipherPost.entities.User;

public class MessageDTO {
    private User sender;
    private Long id;
    private List<String> recipients;
    private List<String> CC_recipients;
    private Timestamp time;
    private int msg_priority;
    private String subject;
    private String content;
    private List<Attachment> attachments;
    public MessageDTO(){}

    public User getSender() {
        return this.sender;
    }

    public List<String> getRecipients() {
        return this.recipients;
    }

    public List<String> getCC_recipients() {
        return CC_recipients;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public int getPriority() {
        return this.msg_priority;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getContent() {
        if (this.content==null) {
            return "";
        }
        return this.content;
    }

    public List<Attachment> getAttachments() {
        return this.attachments;
    }
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMsg_priority(int msg_priority) {
        this.msg_priority = msg_priority;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getMsg_priority() {
        return msg_priority;
    }

    public void setCC_recipients(List<String> CC_recipients) {
        this.CC_recipients = CC_recipients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}



