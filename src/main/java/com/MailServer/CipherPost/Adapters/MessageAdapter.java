package com.MailServer.CipherPost.Adapters;

import com.MailServer.CipherPost.DTOs.MessageDTO;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.Message;

public class MessageAdapter implements Adapter<Message, MessageDTO> {

    @Override
    public MessageDTO toDto(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSender(message.getSender());
        messageDTO.setRecipients(message.getRecipients());
        messageDTO.setTime(message.getTimestamp());
        messageDTO.setMsg_priority(message.getMsg_priority());
        messageDTO.setSubject(message.getSubject());
        messageDTO.setContent(message.getContent());
        messageDTO.setAttachments(message.getAttachments());
        return messageDTO;
    }
}
