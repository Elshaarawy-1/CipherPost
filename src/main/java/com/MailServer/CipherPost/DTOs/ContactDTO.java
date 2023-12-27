package com.MailServer.CipherPost.DTOs;

import com.MailServer.CipherPost.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactDTO {
    public int contact_id;
    public String nickname;
    public List<String> usernames;

    public int getContact_id() {
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
}
