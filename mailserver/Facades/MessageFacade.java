package com.csed.mailserver.mailserver.Facades;

import com.csed.mailserver.mailserver.Clients.Message;
import com.csed.mailserver.mailserver.DTOs.MessageDTO;
import com.csed.mailserver.mailserver.Repositories.MessageRepository;

public class MessageFacade {
    private MessageRepository messageRepository;

    public void sendMessage(MessageDTO sent_msg) {
        Message sent = new Message(sent_msg);
        messageRepository.save(sent);
    }

    public void deleteMessage(int msg_id) {
        messageRepository.delete(msg_id);
    }
}
