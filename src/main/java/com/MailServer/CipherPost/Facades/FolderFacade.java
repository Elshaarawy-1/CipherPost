package com.MailServer.CipherPost.Facades;

import com.MailServer.CipherPost.Services.FolderMessagesService;
import com.MailServer.CipherPost.Services.FolderService;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FolderFacade {
    FolderMessagesService folderMessagesService;
    FolderService folderService;
    public void changeMessageFolder(List<Message> selected_msg, Folder source, Folder destination) {
        folderMessagesService.changeMessageFolder(selected_msg, source, destination);
    }
    public void deleteFolder(Folder folder) {
        folderService.deleteFolder(folder);
    }
    public void renameFolder(Folder old_folder, String new_name) {
        folderService.renameFolder(old_folder, new_name);
    }
    public void createFolder(User user, String name) {
        folderService.createFolder(user, name);
    }
}
