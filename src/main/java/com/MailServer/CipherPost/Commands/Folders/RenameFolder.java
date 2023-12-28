package com.MailServer.CipherPost.Commands.Folders;

import com.MailServer.CipherPost.Facades.FolderFacade;
import com.MailServer.CipherPost.entities.User;


public class RenameFolder implements FolderCommand<Void> {
    private final FolderFacade folderFacade;
    private final User user;
    private final Long folder_id;
    private final String new_name;
    public RenameFolder(FolderFacade folderFacade, User user, Long folder_id, String new_name) {
        this.folderFacade = folderFacade;
        this.user = user;
        this.folder_id = folder_id;
        this.new_name = new_name;

    }
    @Override
    public Void execute() {
        folderFacade.renameFolder(user, folder_id, new_name);
//        folderFacade.createFolder(user, folder_name);
        return null;
    }
}
