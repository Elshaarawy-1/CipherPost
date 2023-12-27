//package com.MailServer.CipherPost.Controller;
//
//import com.MailServer.CipherPost.Services.FolderMessagesService;
//import com.MailServer.CipherPost.Services.FolderService;
//import com.MailServer.CipherPost.entities.Folder;
//import com.MailServer.CipherPost.entities.FolderMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/folders")
//public class FolderController {
//    @Autowired
//    private FolderMessagesService folderMessagesService;
//    @Autowired
//    private FolderService folderService;
//    @GetMapping("/{userId}/{folderName}")
//    public ResponseEntity<List<FolderMessage>> getPaginatedMessages(
//            @PathVariable Long userId,
//            @PathVariable String folderName,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "message.id") String sortField,
//            @RequestParam(defaultValue = "") String contentSearch
//            ) {
//
//        System.out.println("Request received: userId=" + userId + ", folderName=" + folderName);
//        Folder folder = folderService.findFolderByNameAndUserId(folderName, userId);
//        System.out.println("Retrieved folder: " + folder);
//        Page<FolderMessage> searchByContent = folderMessagesService.searchMessagesInFolder(folder, contentSearch, page, size, sortField);
//        System.out.println("Messages page content: " + searchByContent.getContent());
//
//        return new ResponseEntity<>(searchByContent.getContent(), HttpStatus.OK);
//    }
//}
