package com.MailServer.CipherPost.repositories;

import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.Message;
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

    FolderMessage findByFolderAndMessage(Folder source, Message msg);

    Page<FolderMessage> findByFolderAndMessage_ContentContainingIgnoreCase(Folder folder, String search, Pageable pageable);

    Page<FolderMessage> findByFolderAndMessage_ContentContaining(Folder folder, String keyword, Pageable pageable);

    Page<FolderMessage> findByFolderAndMessage_Sender_UsernameContainingIgnoreCase(Folder messageFolder, String searchInput, Pageable pageable);

    Page<FolderMessage> findByFolderAndMessage_SubjectContainingIgnoreCase(Folder messageFolder, String searchInput, Pageable pageable);

    Page<FolderMessage> findByFolderAndMessage_Recipients_UsernameContainingIgnoreCase(Folder messageFolder, String searchInput, Pageable pageable);
}
