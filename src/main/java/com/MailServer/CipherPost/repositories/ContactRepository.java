package com.MailServer.CipherPost.repositories;

import com.MailServer.CipherPost.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUserId(Long userId);

    List<Contact> findByUserIdOrderByNickname(Long userId);

    List<Contact> findByUserIdAndNicknameContainingIgnoreCase(Long userId, String nickname);
}
