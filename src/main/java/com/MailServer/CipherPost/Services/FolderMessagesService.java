package com.MailServer.CipherPost.Services;

import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.repositories.FolderMessagesRepository;
import com.MailServer.CipherPost.repositories.FolderRepository;
import com.MailServer.CipherPost.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderMessagesService {
    @Autowired
    private FolderMessagesRepository folderMessagesRepository;
    public Page<FolderMessage> getPaginatedMessages(Folder folder, int offset, int pageSize) {
        return folderMessagesRepository.findByFolder(folder, PageRequest.of(offset, pageSize));
    }
    public Page<FolderMessage> getPaginatedMessagesWithSorting(Folder folder, int offset, int pageSize, String sortByField) {
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(sortByField));

        return folderMessagesRepository.findByFolder(folder, pageable);
    }
    public Page<FolderMessage> searchMessagesInFolder(Folder folder, String keyword, int offset, int pageSize, String sortByField) {
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(sortByField));
        return folderMessagesRepository.findByFolderAndMessage_ContentContaining(folder, keyword, pageable);
    }


    public void changeMessageFolder(List<Message> selectedMsg, Folder source, Folder destination) {
        for (Message msg : selectedMsg) {
            FolderMessage folderMessage = folderMessagesRepository.findByFolderAndMessage(source, msg);
            folderMessage.setFolder(destination);
            folderMessagesRepository.save(folderMessage);
        }
    }

//    public void deleteMessageFromFolder(Message msg, Folder folder) {
//        FolderMessage folderMessage = folderMessagesRepository.findByFolderAndMessage(folder, msg);
//        if(folder.getFolderName().equals("trash")) {
//            folderMessagesRepository.delete(folderMessage);
//        }
//        else {
//            Folder trashFolder = folderRepository.findByFolderNameAndUser("trash", folder.getUser());
//            folderMessage.setFolder(trashFolder);
//            folderMessagesRepository.save(folderMessage);
//        }
//    }
}