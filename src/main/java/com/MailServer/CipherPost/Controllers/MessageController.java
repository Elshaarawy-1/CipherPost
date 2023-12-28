package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.Adapters.MessageAdapter;
import com.MailServer.CipherPost.Commands.Command;
import com.MailServer.CipherPost.Commands.Messages.ComposeMessage;
import com.MailServer.CipherPost.Commands.Messages.DeleteMessage;
import com.MailServer.CipherPost.Commands.Messages.GetMessages;
import com.MailServer.CipherPost.Commands.Messages.MoveMessage;
import com.MailServer.CipherPost.DTOs.MessageDTO;
import com.MailServer.CipherPost.Facades.FolderFacade;
import com.MailServer.CipherPost.Facades.MessageFacade;
import com.MailServer.CipherPost.Services.UserService;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.entities.User;
import com.MailServer.CipherPost.repositories.FolderRepository;
import com.MailServer.CipherPost.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
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
    @Autowired
    UserService userService;
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageDTO message) {
        List<User> recipients = new ArrayList<>();
        for (String recipient : message.getRecipients()) {
            User user = userService.getUserByUsername(recipient);
            if (user != null) {
                recipients.add(user);
            }
        }
        List<User> CC_recipients = new ArrayList<>();
            if (message.getCC_recipients() != null){
            for (String recipient : message.getCC_recipients()) {
                User user = userService.getUserByUsername(recipient);
                if (user != null) {
                    recipients.add(user);
                }
            }
        }
        if(recipients.isEmpty() ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            Message sent_msg = new Message.MessageBuilder(message).withAttachments(message.getAttachments()).build();
            sent_msg.setRecipients(recipients);
            sent_msg.setCC_recipients(CC_recipients);
            Command<Void> sendCommand = new ComposeMessage(messageFacade, sent_msg);
            sendCommand.execute();
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
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
    public List<MessageDTO> getMessages(
            @PathVariable("folder_id") Long folder_id,
            @RequestParam(defaultValue = "message.timestamp") String sortField,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "") String searchField,
            @RequestParam(defaultValue = "") String keyword
    ) {
        Folder folder = folderRepository.findById(folder_id).orElse(null);
        if (folder == null) {
            return null;
        } else {
            Sort.Direction direction = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
            Command<Page<FolderMessage>> getCommand = new GetMessages(messageFacade, folder, pageable, searchField, keyword);
            Page<FolderMessage> pageRequest = getCommand.execute();
            MessageAdapter adapter = new MessageAdapter();
            List<FolderMessage> folder_msgs= pageRequest.getContent();
            List<MessageDTO> req_msgs = new LinkedList<>();
            for (FolderMessage folder_msg : folder_msgs) {
                req_msgs.add(adapter.toDto(folder_msg.getMessage()));
            }
            return req_msgs;
        }
    }
    @GetMapping("/move/{folder_id}/{msg_id}/{newFolderName}")
    public ResponseEntity<Void> moveMessage(@PathVariable("msg_id") Long msg_id, @PathVariable("folder_id") Long folder_id, @PathVariable("newFolderName") String newFolderName) {
        Folder folder = folderRepository.findById(folder_id).orElse(null);
        Message move_msg = messageRepository.findById(msg_id).orElse(null);
        if (folder != null && move_msg != null) {
            Folder new_folder = folderRepository.findByFolderNameAndUser(newFolderName, folder.getUser());
            if (new_folder == null) {
                new_folder = new Folder(folder.getUser(), newFolderName);
            }
            Command<Void> moveCommand = new MoveMessage(messageFacade, move_msg, folder, new_folder);
            moveCommand.execute();

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}