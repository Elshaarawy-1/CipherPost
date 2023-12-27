package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.Commands.Command;
import com.MailServer.CipherPost.Commands.Messages.ComposeMessage;
import com.MailServer.CipherPost.Commands.Messages.DeleteMessage;
import com.MailServer.CipherPost.Commands.Messages.GetMessages;
import com.MailServer.CipherPost.Commands.Messages.MoveMessage;
import com.MailServer.CipherPost.DTOs.MessageDTO;
import com.MailServer.CipherPost.Facades.FolderFacade;
import com.MailServer.CipherPost.Facades.MessageFacade;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.repositories.FolderRepository;
import com.MailServer.CipherPost.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    MessageFacade messageFacade;
    @Autowired
    FolderFacade folderFacade;
    @Autowired
    FolderRepository folderRepository;
    @Autowired
    MessageRepository messageRepository;
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageDTO message) {
        Message sent_msg = new Message.MessageBuilder(message).withAttachments(message.getAttachments()).build();
        Command<Void> sendCommand = new ComposeMessage(messageFacade, sent_msg);
        sendCommand.execute();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{folder_id}/{msg_id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("msg_id") Long msg_id, @PathVariable("folder_id") Long folder_id) {
        Folder folder = folderRepository.findById(folder_id).orElse(null);
        Message delete_msg = messageRepository.findById(msg_id).orElse(null);
        if (folder == null || delete_msg == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Command<Void> deleteCommand = new DeleteMessage(messageFacade, delete_msg, folder);
            deleteCommand.execute();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/get/{folder_id}")
    public List<FolderMessage> getMessages(
            @PathVariable("folder_id") Long folder_id,
            @RequestParam(defaultValue = "message.timestamp") String sortField,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(defaultValue = "") String searchField,
            @RequestParam(defaultValue = "") String keyword
    ) {
        Folder folder = folderRepository.findById(folder_id).orElse(null);
        if (folder == null) {
            return (List<FolderMessage>) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Sort.Direction direction = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            Command<Page<FolderMessage>> getCommand = new GetMessages(messageFacade, folder, sortField, direction, searchField, keyword);
            Page<FolderMessage> page = getCommand.execute();
            return page.getContent();
        }
    }
    @GetMapping("/move/{folder_id}/{msg_id}/{new_folder_id}")
    public ResponseEntity<Void> moveMessage(@PathVariable("msg_id") Long msg_id, @PathVariable("folder_id") Long folder_id, @PathVariable("new_folder_id") Long new_folder_id) {
        Folder folder = folderRepository.findById(folder_id).orElse(null);
        Folder new_folder = folderRepository.findById(new_folder_id).orElse(null);
        Message move_msg = messageRepository.findById(msg_id).orElse(null);
        if (folder == null || new_folder == null || move_msg == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Command<Void> moveCommand = new MoveMessage(messageFacade, move_msg, folder, new_folder);
            moveCommand.execute();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
