package com.MailServer.CipherPost.repositories;

import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface FolderMessagesRepository extends JpaRepository<FolderMessage, Long> {
    @Query("SELECT fm FROM folder_messages fm " +
            "JOIN FETCH fm.folder f " +
            "JOIN FETCH fm.message m " +
            "WHERE f.folderName = :folderName AND fm.addTime < :thirtyDaysAgo")
    List<FolderMessage> findByFolder_FolderNameAndAddTimeBefore(String folderName, Timestamp thirtyDaysAgo);
    Page<FolderMessage> findByFolder(Folder folder, Pageable pageable);


    //Not Tested yet Next four
//    Page<FolderMessage> findByMessage_Content(String content, Pageable pageable);
//    Page<FolderMessage> findByMessage_Content
    Page<FolderMessage> findByFolderAndMessage_ContentContainingIgnoreCase(Folder folder, String search, Pageable pageable);
//    Page<FolderMessage> findByFolderAndMessage_Sender(Folder folder, User user, Pageable pageable);

    Page<FolderMessage> findByFolderAndMessage_ContentContaining(Folder folder, String keyword, Pageable pageable);
}
