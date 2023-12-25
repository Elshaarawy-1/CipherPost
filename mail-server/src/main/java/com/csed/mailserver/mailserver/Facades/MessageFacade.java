package com.csed.mailserver.mailserver.Facades;

import com.csed.mailserver.mailserver.Clients.Folder;
import com.csed.mailserver.mailserver.Clients.Message;

import java.util.List;

public class MessageFacade {

    public void sendMessage(Message sent_msg) {
//        messageRepository.saveMessage(sent_msg);
    }

    public void deleteMessage(int[] msg_ids) {
//        messageRepository.deleteMessage(msg_ids);
    }

    public List<Message> sortMessages(Folder currnt_folder, String critieria) {
        return null;
    }
    public void searchMessages(Folder currnt_folder, String search_input) {
    }
}
