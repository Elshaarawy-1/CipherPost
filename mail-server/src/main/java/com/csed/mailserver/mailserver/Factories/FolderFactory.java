package com.csed.mailserver.mailserver.Factories;

import com.csed.mailserver.mailserver.Clients.*;

public class FolderFactory {
    public Folder createFolder(String folder_name) {
        if (folder_name.equalsIgnoreCase("inbox")) {
            return new Inbox(folder_name);
        } else if (folder_name.equalsIgnoreCase("sent")) {
            return new Sent(folder_name);
        } else if (folder_name.equalsIgnoreCase("trash")) {
            return new Trash(folder_name);
        } else if (folder_name.equalsIgnoreCase("drafts")) {
            return new Drafts(folder_name);
        } else {
            return new CustomFolder(folder_name);
        }
    }
}

