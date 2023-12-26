package com.MailServer.CipherPost.repositories;

import com.MailServer.CipherPost.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
