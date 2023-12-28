package com.MailServer.CipherPost.Commands.Folders;

import com.MailServer.CipherPost.Facades.FolderFacade;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.User;

import java.util.List;

public class DeleteFolder implements FolderCommand<Void> {
    private final FolderFacade folderFacade;
    private final User user;
    private final Long folder_id;
    public DeleteFolder(FolderFacade folderFacade, User user, Long folder_id) {
        this.folderFacade = folderFacade;
        this.user = user;
        this.folder_id = folder_id;

    }
    @Override
    public Void execute() {
        folderFacade.deleteFolderByUserAndFolderId(user, folder_id);
        return null;
    }
}
