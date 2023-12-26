package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.DTOs.FolderDTO;
import com.MailServer.CipherPost.DTOs.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*/api/folders" )
public class FolderController {

    @PostMapping("/create")
    public ResponseEntity<Void> createFolder(@RequestBody FolderDTO new_folder) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/rename")
    public ResponseEntity<Void> renameFolder(@RequestBody FolderDTO folder, @RequestBody String new_name) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/delete/{folder_id}")
    public ResponseEntity<Void> deleteFolder(@PathVariable int folder_id) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}