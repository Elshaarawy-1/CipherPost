package com.MailServer.CipherPost.Services;

import com.MailServer.CipherPost.Observers.MessageObserver;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.entities.User;
import com.MailServer.CipherPost.repositories.FolderMessagesRepository;
import com.MailServer.CipherPost.repositories.FolderRepository;
import com.MailServer.CipherPost.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;




@Service
public class MessageService implements MessageObserver {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    FolderRepository folderRepository;
    @Autowired
    FolderMessagesRepository folderMessagesRepository;

    @Override
    public void update(Message message) {
        saveMessage(message);
        saveMessageForRecipients(message);
        saveMessageForSender(message);
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    private void saveMessageForRecipients(Message message) {
        for (User recipient : message.getRecipients()) {
            Folder userFolder = getUserFolderByUser("inbox", recipient);
            if (userFolder != null) {
                saveMessageToUserFolder(message, userFolder);
            }
        }
    }

    private void saveMessageForSender(Message message) {
        User sender = message.getSender();
        Folder senderFolder = getUserFolderByUser("sent", sender);
        if (senderFolder != null) {
            saveMessageToUserFolder(message, senderFolder);
        }
    }

    private void saveMessageToUserFolder(Message message, Folder userFolder) {
        FolderMessage folderMessage = new FolderMessage(message, userFolder, new Timestamp(System.currentTimeMillis()));
        folderMessagesRepository.save(folderMessage);
    }

    private Folder getUserFolderByUser(String folderName, User user) {
        return folderRepository.findByFolderNameAndUser(folderName.toLowerCase(), user);
    }
}