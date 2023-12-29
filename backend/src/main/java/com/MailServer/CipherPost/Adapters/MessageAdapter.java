package com.MailServer.CipherPost.Adapters;

import com.MailServer.CipherPost.DTOs.ContactDTO;
import com.MailServer.CipherPost.DTOs.MessageDTO;
import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.entities.User;

import java.util.LinkedList;
import java.util.List;

public class MessageAdapter implements Adapter<Message, MessageDTO> {

    @Override
    public MessageDTO toDto(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSender(message.getSender());
        messageDTO.setRecipients(new LinkedList<>());
        for (User user : message.getRecipients()) {
            messageDTO.getRecipients().add(user.getUsername());
        }
        messageDTO.setCC_recipients(new LinkedList<>());
        for (User user : message.getCC_recipients()) {
            messageDTO.getCC_recipients().add(user.getUsername());
        }
        messageDTO.setTime(message.getTimestamp());
        messageDTO.setMsg_priority(message.getMsg_priority());
        messageDTO.setSubject(message.getSubject());
        messageDTO.setContent(message.getContent());
        messageDTO.setAttachments(message.getAttachments());
        messageDTO.setId(message.getId());
        return messageDTO;
    }
    public List<MessageDTO> toListDTO(List<Message> messages) {
        List<MessageDTO> msgs_dto = new LinkedList<>();
        for (Message msg : messages){
            msgs_dto.add(this.toDto(msg));
        }
        return msgs_dto;
    }
}
