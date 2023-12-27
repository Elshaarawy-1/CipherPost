package com.MailServer.CipherPost.Facades;

import com.MailServer.CipherPost.Services.FolderMessagesService;
import com.MailServer.CipherPost.Services.FolderService;
import com.MailServer.CipherPost.Services.MessageService;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageFacade {
    @Autowired
    MessageService messageService;
    @Autowired
    FolderMessagesService folderMessagesService;
    @Autowired
    FolderService folderService;

    public void sendMessage(Message sent_msg) {
        this.messageService.saveMessage(sent_msg);
    }
    public void deleteMessageFromFolder(Message msg, Folder folder) {
//        folderMessagesService.deleteMessageFromFolder(msg, folder);
        folderService.deleteMessageFromFolder(msg, folder);
    }

    public List<Message> sortMessages(Folder currnt_folder, String critieria) {
        return null;
    }

    public void searchMessages(Folder messageFolder, String criteria, String searchInput) {
        folderService.searchMessages(messageFolder, criteria, searchInput);
    }

//    public void searchMessages(Folder currnt_folder, String search_input) {
//    }
}
