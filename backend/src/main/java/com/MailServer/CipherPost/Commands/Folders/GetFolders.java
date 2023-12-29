package com.MailServer.CipherPost.Commands.Folders;

import com.MailServer.CipherPost.Facades.FolderFacade;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.User;

import java.util.List;

public class GetFolders implements FolderCommand<List<Folder>> {
    private final FolderFacade folderFacade;
    private final User user;
    public GetFolders(FolderFacade folderFacade, User user) {
        this.folderFacade = folderFacade;
        this.user = user;
    }
    @Override
    public List<Folder> execute() {
        return folderFacade.getFolders(user);
    }
}
