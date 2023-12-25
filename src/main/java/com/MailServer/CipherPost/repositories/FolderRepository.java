package com.MailServer.CipherPost.repositories;

import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    Folder findByFolderNameAndUser(String folderName, User user);
//    Folder findByFolderNameAndUser_Id(String folderName, Long id);
}
