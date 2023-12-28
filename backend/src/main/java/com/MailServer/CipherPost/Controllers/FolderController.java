package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.Adapters.ContactAdapter;
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
    @GetMapping("/get/{user_id}")
    public List<FolderDTO> getFolders(@PathVariable Long user_id) {
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
    @PostMapping("/create/{user_id}")
        public ResponseEntity<Void> createFolder(@PathVariable Long user_id, @RequestBody FolderDTO new_folder) {
        // TODO: creating folder, takes folderDTO description to be created, returns void response
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

    @PostMapping("/rename/{user_id}/{folder_id}")
    public ResponseEntity<Void> renameFolder(@PathVariable Long user_id , @PathVariable Long folder_id, @RequestBody String new_name ) {
        // TODO: creating folder, takes folderDTO to be renamed, new name, returns void response
        User user = userService.getUserById(user_id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
//            String folder_name = folder.getFolderName();
            Command<Void> renameCommand = new RenameFolder(folderFacade, user, folder_id, new_name);
            renameCommand.execute();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/delete/{user_id}/{folder_id}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long user_id, @PathVariable Long folder_id) {
        // TODO: creating folder, takes folder id to be deleted, new name, returns void response
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