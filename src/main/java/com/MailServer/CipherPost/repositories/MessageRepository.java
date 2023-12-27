package com.MailServer.CipherPost.repositories;

import com.MailServer.CipherPost.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
