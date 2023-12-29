package com.MailServer.CipherPost.DTOs;

public class FolderDTO {
    private String folder_name;
    private Long folder_id;
//    private int folder_messages;

    public Long getFolder_id() {
        return folder_id;
    }

    public String getFolderName() {
        return folder_name;
    }

    public void setFolder_id(Long folder_id) {
        this.folder_id = folder_id;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public String getFolder_name() {
        return folder_name;
    }

//    public int getFolder_messages() {
//        return folder_messages;
//    }
//
//    public void setFolder_messages(int folder_messages) {
//        this.folder_messages = folder_messages;
//    }
}