package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.DTOs.FolderDTO;
import com.MailServer.CipherPost.DTOs.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    @PostMapping("/create")
    public ResponseEntity<Void> createFolder(@RequestBody FolderDTO new_folder) {
        // TODO: creating folder, takes folderDTO description to be created, returns void response
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/rename")
    public ResponseEntity<Void> renameFolder(@RequestBody FolderDTO folder, @RequestBody String new_name) {
        // TODO: creating folder, takes folderDTO to be renamed, new name, returns void response
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/delete/{folder_id}")
    public ResponseEntity<Void> deleteFolder(@PathVariable int folder_id) {
        // TODO: creating folder, takes folder id to be deleted, new name, returns void response
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}