package com.MailServer.CipherPost.entities;

import jakarta.persistence.*;

@Entity(name = "user_contacts")
public class UserContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Long id;

}
