package com.MailServer.CipherPost.DTOs;

public class FolderDTO {
    private String folder_name;
    private Long folder_id;

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

}