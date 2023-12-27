package com.MailServer.CipherPost.entities;

import jakarta.persistence.*;


// ... (existing imports)

import jakarta.persistence.*;

@Entity
@Table(name = "message_attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private int id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "content_type")
    private String contentType;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "DATA", nullable = true)
    private byte[] data;

    public Attachment(String file, String contentType, byte[] bytes) {
        this.filename = file;
        this.contentType = contentType;
        this.data = bytes;
    }

    public Attachment() {

    }


    public int getId() {
        return id;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}