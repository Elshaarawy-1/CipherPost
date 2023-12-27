package com.MailServer.CipherPost.DTOs;

import com.MailServer.CipherPost.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactDTO {
    private Long contact_id;
    private String nickname;
    private List<String> usernames;

    public Long getContact_id() {
        return contact_id;
    }

    public String getNickname() {
        return nickname;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public List<String> getContact_usernames() {
        return usernames;
    }

    public void setContact_id(Long contact_id) {
        this.contact_id = contact_id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }
}
