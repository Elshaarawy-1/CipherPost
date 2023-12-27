package com.MailServer.CipherPost.Adapters;

import com.MailServer.CipherPost.DTOs.ContactDTO;
import com.MailServer.CipherPost.entities.Contact;

public class ContactAdapter implements Adapter<Contact,ContactDTO>{
    @Override
    public ContactDTO toDto(Contact source) {
        ContactDTO dto = new ContactDTO();
        dto.setContact_id(source.getId());
        dto.setNickname(source.getNickname());
        dto.setUsernames(source.getContact_usernames());
        return dto;
    }
}
