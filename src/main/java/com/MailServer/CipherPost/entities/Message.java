package com.MailServer.CipherPost.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


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
            name = "content",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String content;

    public Message() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.recipients = new ArrayList<>();
    }

    public Message(User sender, List<User> recipients, String content, Timestamp timestamp) {
        this.sender = sender;
        this.recipients = new ArrayList<>(recipients);
        this.content = content;
//        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.timestamp = timestamp;
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
}