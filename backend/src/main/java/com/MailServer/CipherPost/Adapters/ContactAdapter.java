package com.MailServer.CipherPost.Adapters;

import com.MailServer.CipherPost.DTOs.ContactDTO;
import com.MailServer.CipherPost.entities.Contact;

import java.util.LinkedList;
import java.util.List;

public class ContactAdapter implements Adapter<Contact,ContactDTO>{
    @Override
    public ContactDTO toDto(Contact source) {
        ContactDTO dto = new ContactDTO();
        dto.setContact_id(source.getId());
        dto.setNickname(source.getNickname());
        dto.setUsernames(source.getContact_usernames());
        return dto;
    }
    public List<ContactDTO> toListDTO(List<Contact> contacts) {
        List<ContactDTO> contacts_dto = new LinkedList<>();
        ContactAdapter adapter = new ContactAdapter();
        for (Contact contact : contacts){
            contacts_dto.add(adapter.toDto(contact));
        }
        return contacts_dto;
    }
}
