package com.MailServer.CipherPost.entities;
import com.MailServer.CipherPost.DTOs.MessageDTO;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component  // Add this annotation
@Entity(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "msg_id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "message_recipients",
            joinColumns = @JoinColumn(name = "msg_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> recipients = new ArrayList<>();
    @Column(
            name = "send_time",
            nullable = false
    )
    private Timestamp timestamp;
    @Column(
            name = "subject",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String subject;

    @Column(
            name = "content",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String content;
    @Column(
            name = "msg_priority",
            nullable = false
    )
    private int msg_priority;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "msg_id")
    private List<Attachment> attachments = new ArrayList<>();

    public Message() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.recipients = new ArrayList<>();
    }

    public Message(User sender, List<User> recipients, String subject, String content, Timestamp timestamp) {
        this.sender = sender;
        this.recipients = new ArrayList<>(recipients);
        this.subject = subject;
        this.content = content;
//        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.timestamp = timestamp;
    }
    public Message(User sender, List<User> recipients, String subject, String content, List<Attachment> attachments, Timestamp timestamp) {
        this.sender = sender;
        this.recipients = new ArrayList<>(recipients);
        this.subject = subject;
        this.content = content;
//        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.attachments.addAll(attachments);
        this.timestamp = timestamp;
    }
public Message(MessageBuilder builder) {
    this.sender = builder.sender;
    this.recipients = builder.recipients;
    this.timestamp = builder.time;
    this.msg_priority = builder.msg_priority;
    this.subject = builder.subject;
    this.content = builder.content;
    this.attachments = builder.attachments;
}
    public static class MessageBuilder {
        private User sender;
        private List<User> recipients;
        private Timestamp time;
        private int msg_priority;
        private String subject;
        private String content;
        private List<Attachment> attachments = new ArrayList<>();

        public MessageBuilder(MessageDTO msg) {
            this.sender = msg.getSender();
            this.recipients = msg.getRecipients();
            this.time = new Timestamp(System.currentTimeMillis());
            this.msg_priority = msg.getPriority();
            this.subject = msg.getSubject();
            this.content = msg.getContent();
        }

        public MessageBuilder withAttachments(List<Attachment> attachments) {
            this.attachments.addAll(attachments);
            return this;
        }
        public Message build() {
            return new Message(this);
        }
    }

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }
    public List<Attachment> getAttachments() {
        return attachments;
    }

    public String getSubject() {
        return subject;
    }

    public int getMsg_priority() {
        return msg_priority;
    }
}