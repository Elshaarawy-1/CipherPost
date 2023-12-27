package com.MailServer.CipherPost.repositories;

import com.MailServer.CipherPost.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
