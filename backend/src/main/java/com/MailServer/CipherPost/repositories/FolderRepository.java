package com.MailServer.CipherPost.repositories;

import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    Folder findByFolderNameAndUser(String folderName, User user);
    Folder findFolderByFolderNameAndUser_Username(String folderName, String username);

    List<Folder> findFolderByUser(User user);

    Folder findFolderByIdAndUser(Long id, User user);
//    void findByUser(User user);
//    Folder findByFolderNameAndUser_Id(String folderName, Long id);
}
