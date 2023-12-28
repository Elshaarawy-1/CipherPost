package com.MailServer.CipherPost.Facades;

import com.MailServer.CipherPost.Services.FolderMessagesService;
import com.MailServer.CipherPost.Services.FolderService;
import com.MailServer.CipherPost.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FolderFacade {
    @Autowired
    FolderMessagesService folderMessagesService;
    @Autowired
    FolderService folderService;
    public void createFolder(User user, String name) {
        folderService.createFolder(user, name);
    }

    public List<Folder> getFolders(User user) {
        return folderService.getFolders(user);
    }

    public void deleteFolderByName(User user, String folderName) {
        Folder folder = folderService.getFolderByNameAndUser(folderName, user);
        if (folder != null) {
            folderService.deleteFolder(folder);
        }
    }
    public void deleteFolderByUserAndFolderId(User user, Long folderId) {
        Folder folder = folderService.getFolderByIdAndUser(folderId, user);
        if (folder != null) {
            folderService.deleteFolder(folder);
        }
    }

    public void renameFolder(User user, Long folder_id, String newName) {
        Folder folder = folderService.getFolderByIdAndUser(folder_id, user);
        if (folder != null) {
            folderService.renameFolder(folder, newName);
        }
    }
}
