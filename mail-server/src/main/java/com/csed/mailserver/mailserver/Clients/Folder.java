package com.csed.mailserver.mailserver.Clients;

public class Folder {
    private String folder_name;
    private int folder_id;
    Folder(){}
    Folder(String name){
        this.folder_name = name;
    }
    public int getFolderId() {
        return this.folder_id;
    }
    public String getFolderName() {
        return this.folder_name;
    }

    public void setFolderId(int folder_id) {
        this.folder_id = folder_id;
    }

    public void setFolderName(String folder_name) {
        this.folder_name = folder_name;
    }
}
