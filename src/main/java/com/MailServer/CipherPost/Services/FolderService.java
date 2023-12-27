package com.MailServer.CipherPost.Services;

import com.MailServer.CipherPost.entities.*;
import com.MailServer.CipherPost.repositories.FolderMessagesRepository;
import com.MailServer.CipherPost.repositories.FolderRepository;
import com.MailServer.CipherPost.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FolderMessagesRepository folderMessagesRepository;

    public Folder findFolderByNameAndUser(String folderName, User user) {
        return folderRepository.findByFolderNameAndUser(folderName, user);
    }

    public Folder findFolderByNameAndUserId(String folderName, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        User user = userOptional.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        return findFolderByNameAndUser(folderName, user);
    }

    public void cleanUpTrashFolder() {
        System.out.println("Clean Up Time");
        Timestamp thirtyDaysAgo = new Timestamp(System.currentTimeMillis() - (30L * 24L * 60L * 60L * 1000L));
        List<FolderMessage> messagesToDelete = folderMessagesRepository.findByFolder_FolderNameAndAddTimeBefore("inbox", thirtyDaysAgo);
        folderMessagesRepository.deleteAll(messagesToDelete);
    }

    public void deleteFolder(Folder folder) {
        folderRepository.delete(folder);
    }

    public void renameFolder(Folder oldFolder, String newName) {
        oldFolder.setFolderName(newName);
        folderRepository.save(oldFolder);
    }

    public void createFolder(User user, String name) {
        Folder folder = new Folder(user, name);
        folderRepository.save(folder);
    }

    public void deleteMessageFromFolder(Message msg, Folder folder) {
        FolderMessage folderMessage = folderMessagesRepository.findByFolderAndMessage(folder, msg);
        if (folder.getFolderName().equals("trash")) {
            folderMessagesRepository.delete(folderMessage);
        } else {
            Folder trashFolder = folderRepository.findByFolderNameAndUser("trash", folder.getUser());
            folderMessage.setFolder(trashFolder);
            folderMessagesRepository.save(folderMessage);
        }

    }

    public void searchMessages(Folder messageFolder, String criteria, String searchInput) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("addTime").descending());
        Page<FolderMessage> folderMessages = switch (criteria) {
            case "content" ->
                    folderMessagesRepository.findByFolderAndMessage_ContentContainingIgnoreCase(messageFolder, searchInput, pageable);
            case "sender" ->
                    folderMessagesRepository.findByFolderAndMessage_Sender_UsernameContainingIgnoreCase(messageFolder, searchInput, pageable);
            case "recipients" ->
                    folderMessagesRepository.findByFolderAndMessage_Recipients_UsernameContainingIgnoreCase(messageFolder, searchInput, pageable);
            default -> folderMessagesRepository.findByFolderAndMessage_SubjectContainingIgnoreCase(messageFolder, searchInput, pageable);

        };
        System.out.println(folderMessages.getContent());
    }

    public List<FolderMessage> getMessages(Folder folder) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("addTime").descending());
        return folderMessagesRepository.findByFolder(folder, pageable).getContent();
    }
}