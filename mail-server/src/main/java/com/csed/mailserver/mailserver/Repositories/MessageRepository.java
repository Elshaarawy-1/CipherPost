package com.csed.mailserver.mailserver.Repositories;

import com.csed.mailserver.mailserver.Clients.Folder;
import com.csed.mailserver.mailserver.Clients.Message;

import java.util.List;

public class MessageRepository {

    /**
     * Save the message in the database
     * @param message: newly sent message
     */
    public void saveMessage(Message message) {
        // implementation
    }

    /**
     * Delete Message IDs
     * @param msgIds : id of messages to be deleted
     */
    public void deleteMessage(int[] msgIds) {
        // implementation
    }

    public List<Message> sortMessages(Folder currnt_folder, String criteria) {
        // implementation
        return null;
    }
    public List<Message> searchMessages(Folder currnt_folder, String search_input) {
        // implementation
        return null;
    }
}
