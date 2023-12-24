package com.MailServer.CipherPost.CompositeKeys;

import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.Message;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class FolderMessageKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "msg_id")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;

}
