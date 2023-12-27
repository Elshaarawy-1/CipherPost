package com.MailServer.CipherPost.Facades;

import com.MailServer.CipherPost.Services.FolderMessagesService;
import com.MailServer.CipherPost.Services.FolderService;
import com.MailServer.CipherPost.Services.MessageService;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
        this.messageService.update(sent_msg);
    }
    public void deleteMessageFromFolder(Message msg, Folder folder) {
        folderService.deleteMessageFromFolder(msg, folder);
    }
    public void moveMessageToFolder(Message msg, Folder source, Folder destination) {
        folderService.moveMessageToFolder(msg, source, destination);
    }
    public Page<FolderMessage> getMessages(Folder folder, String sortField, Sort.Direction direction, String searchField, String keyword) {
        return folderMessagesService.getPaginatedMessagesWithSortingAndSearch(folder, 0, 10, sortField, direction, searchField, keyword);
    }
}
