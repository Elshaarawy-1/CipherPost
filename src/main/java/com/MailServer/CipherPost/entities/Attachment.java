package com.MailServer.CipherPost.entities;

import jakarta.persistence.*;


// ... (existing imports)

import jakarta.persistence.*;

@Entity(name = "attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "msg_id")
    private Message message;

    @Column(name = "file_name", nullable = false)
    private String fileName;



    public Attachment() {
    }

    public Long getId() {
        return id;
    }
    public String getFileName() {
        return fileName;
    }
}


////@Entity(name = "attachments")
//public class Attachment {
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @Column(name = "attachment_id")
////    private Long id;
//
////    @Column(name = "file_name", nullable = false)
//    private String fileName;
//    private String content;
//
//    // You can store the attachment content in various ways, such as byte array, file path, etc.
//    // For simplicity, let's use a byte array here
////    @Lob
////    @Column(name = "content", nullable = false)
////    private byte[] content;
//
//    // Define a Many-to-One relationship with Message
////    @ManyToOne
////    @JoinColumn(name = "msg_id")
////    private Message message;
//
//    // Constructors, getters, and setters
//    public Attachment(){
//
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public String getContent() {
//        return content;
//    }
//}
