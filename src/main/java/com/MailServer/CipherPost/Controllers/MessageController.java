package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.Commands.Messages.DeleteMessage;
import com.MailServer.CipherPost.Facades.MessageFacade;
import com.MailServer.CipherPost.entities.Attachment;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.Commands.Command;
import com.MailServer.CipherPost.Commands.Messages.ComposeMessage;
import com.MailServer.CipherPost.DTOs.MessageDTO;
import com.MailServer.CipherPost.entities.User;
import com.MailServer.CipherPost.repositories.FolderRepository;
import com.MailServer.CipherPost.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*" )
public class MessageController {
    @Autowired
    MessageFacade messageFacade;
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

    @DeleteMapping("/delete/{folder_id}")
    public ResponseEntity<Void> deleteMessage(@RequestBody int[] msg_ids, @PathVariable("folder_id") Long folder_id) {
        Folder folder = folderRepository.findById(folder_id).orElse(null);
        if (folder == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            for (int msg_id : msg_ids) {
                Message delete_msg = messageRepository.findById((long) msg_id).orElse(null);

                if (delete_msg == null) {
                    continue;
                }
                try {
                    Command<Void> deleteCommand = new DeleteMessage(messageFacade, delete_msg, folder);
                    deleteCommand.execute();
                } catch (Exception e) {
                    // Log the exception or handle the case where message deletion fails
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}
