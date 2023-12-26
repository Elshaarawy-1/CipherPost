package com.MailServer.CipherPost.entities;


import jakarta.persistence.*;

@Entity(name = "folders")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "folder_id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "folder_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String folderName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Folder(User user, String folderName) {
        this.folderName = folderName;
        this.user = user;
    }

    public Folder() {

    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String newName) {
        this.folderName = newName;
    }

    public User getUser() {
        return user;
    }
}
