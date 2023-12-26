package com.MailServer.CipherPost.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Optional;

@Entity(name = "folder_messages")
public class FolderMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "msg_id")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @Column(name = "add_time", nullable = false)
    private Timestamp addTime;


    public FolderMessage(Message message, Folder folder, Timestamp timestamp) {
        this.message = message;
        this.folder = folder;
//        this.addTime = new Timestamp(System.currentTimeMillis());
        this.addTime = timestamp;
    }

    public FolderMessage() {

    }

    public Message getMessage() {
        return message;
    }

    public Timestamp getAddTime() {
        return addTime;
    }


    public void setFolder(Folder destination) {
        this.folder = destination;
    }
}
