package com.MailServer.CipherPost.entities;

import com.MailServer.CipherPost.DTOs.ContactDTO;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Entity(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;
    @Column(name = "nickname")
    private String nickname;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "contact_usernames",
            joinColumns = @JoinColumn(name = "contact_id")
    )
    @Column(name = "username")
    private List<String> contact_usernames = new ArrayList<>();

    public Contact(String nickname, User user, List<String> users) {
        this.nickname = nickname;
        this.user = user;
        this.contact_usernames = users;
    }
    public Contact(User user, ContactDTO contactDTO) {
        this.nickname = contactDTO.getNickname();
        this.user = user;
        this.contact_usernames = contactDTO.getUsernames();
    }
    public Contact() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User getUser() {
        return user;
    }

    public List<String> getContact_usernames() {
        return contact_usernames;
    }

    public void setContact_usernames(List<String> contact_usernames) {
        this.contact_usernames = contact_usernames;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
