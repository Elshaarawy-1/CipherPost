package com.MailServer.CipherPost.Facades;

import com.MailServer.CipherPost.Services.MessageService;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageFacade {
    @Autowired
    MessageService messageService;

    public void sendMessage(Message sent_msg) {
        this.messageService.saveMessage(sent_msg);
    }

//    public void deleteMessage(int[] msg_ids) {
//        messageRepository.deleteMessage(msg_ids);
//    }

    public List<Message> sortMessages(Folder currnt_folder, String critieria) {
        return null;
    }
    public void searchMessages(Folder currnt_folder, String search_input) {
    }
}
