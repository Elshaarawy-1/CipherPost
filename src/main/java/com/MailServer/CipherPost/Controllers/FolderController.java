package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.Adapters.FolderAdapter;
import com.MailServer.CipherPost.Commands.Command;
import com.MailServer.CipherPost.Commands.Folders.CreateFolder;
import com.MailServer.CipherPost.Commands.Folders.GetFolders;
import com.MailServer.CipherPost.Commands.Folders.DeleteFolder;
import com.MailServer.CipherPost.Commands.Folders.RenameFolder;
import com.MailServer.CipherPost.DTOs.FolderDTO;
import com.MailServer.CipherPost.Facades.FolderFacade;
import com.MailServer.CipherPost.Services.UserService;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/folders")
@CrossOrigin("*")
public class FolderController {
    @Autowired
    UserService userService;
    @Autowired
    FolderFacade folderFacade;

    private Long extractUserId(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return Long.parseLong(authorizationHeader.substring(7));
        }
        throw new IllegalArgumentException("Invalid Authorization header format");
    }
    @GetMapping("/get")
    public List<FolderDTO> getFolders(@RequestHeader("Authorization") String authorizationHeader) {
        Long user_id = extractUserId(authorizationHeader);
        User user = userService.getUserById(user_id);
        if (user == null) {
            return null;
        }
        else {
            Command<List<Folder>> getCommand = new GetFolders(folderFacade, user);
            List<Folder> folders = getCommand.execute();
            FolderAdapter adapter = new FolderAdapter();
            return adapter.toListDTO(folders);
        }

    }
    @PostMapping("/create")
        public ResponseEntity<Void> createFolder(@RequestHeader("Authorization") String authorizationHeader, @RequestBody FolderDTO new_folder) {
        Long user_id = extractUserId(authorizationHeader);
        User user = userService.getUserById(user_id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            String folder_name = new_folder.getFolderName();
            Command<Void> createCommand = new CreateFolder(folderFacade, user, folder_name);
            createCommand.execute();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/rename/{folder_id}")
    public ResponseEntity<Void> renameFolder(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long folder_id, @RequestBody String new_name ) {
        Long user_id = extractUserId(authorizationHeader);
        User user = userService.getUserById(user_id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            Command<Void> renameCommand = new RenameFolder(folderFacade, user, folder_id, new_name);
            renameCommand.execute();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/delete/{folder_id}")
    public ResponseEntity<Void> deleteFolder(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long folder_id) {
        Long user_id = extractUserId(authorizationHeader);
        User user = userService.getUserById(user_id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            Command<Void> deleteCommand = new DeleteFolder(folderFacade, user, folder_id);
            deleteCommand.execute();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}