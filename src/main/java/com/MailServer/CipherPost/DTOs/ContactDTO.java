package com.MailServer.CipherPost.DTOs;

import com.MailServer.CipherPost.entities.User;

import java.util.List;

public class ContactDTO {
    public int contact_id;
    public String nickname;
    public List<User> usernames;

    public int getContact_id() {
        return contact_id;
    }

    public String getContact_name() {
        return nickname;
    }

    public List<User> getUsernames() {
        return usernames;
    }
}
