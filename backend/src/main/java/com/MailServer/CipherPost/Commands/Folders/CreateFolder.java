package com.MailServer.CipherPost.Commands.Folders;

import com.MailServer.CipherPost.Facades.FolderFacade;
import com.MailServer.CipherPost.entities.User;


public class CreateFolder implements FolderCommand<Void> {
    private final FolderFacade folderFacade;
    private final User user;
    private final String folder_name;
    public CreateFolder(FolderFacade folderFacade, User user, String folder_name) {
        this.folderFacade = folderFacade;
        this.user = user;
        this.folder_name = folder_name;
    }
    @Override
    public Void execute() {
        folderFacade.createFolder(user, folder_name);
        return null;
    }
}
