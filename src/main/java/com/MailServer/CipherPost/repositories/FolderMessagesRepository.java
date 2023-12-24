package com.MailServer.CipherPost.repositories;

import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface FolderMessagesRepository extends JpaRepository<FolderMessage, Long> {
    @Query("SELECT fm FROM folder_messages fm " +
            "JOIN FETCH fm.folder f " +
            "JOIN FETCH fm.message m " +
            "WHERE f.folderName = :folderName AND fm.addTime < :thirtyDaysAgo")
    List<FolderMessage> findByFolder_FolderNameAndAddTimeBefore(String folderName, Timestamp thirtyDaysAgo);
    Page<FolderMessage> findByFolder(Folder folder, Pageable pageable);

}
