package com.MailServer.CipherPost.Controllers;

import com.MailServer.CipherPost.Facades.MessageFacade;
import com.MailServer.CipherPost.entities.Attachment;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.Commands.Command;
import com.MailServer.CipherPost.Commands.Messages.ComposeMessage;
import com.MailServer.CipherPost.DTOs.MessageDTO;
import com.MailServer.CipherPost.entities.User;
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
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageDTO message) {
        User dumy = new User("dumy");
        List<Attachment> arrayattachment = new ArrayList<>();
        arrayattachment.add(new Attachment());
        arrayattachment.add(new Attachment());
        arrayattachment.add(new Attachment());
        Message sent_msg = new Message.MessageBuilder(message).withAttachments(message.getAttachments()).build();
        Command<Void> sendCommand = new ComposeMessage(messageFacade, sent_msg);
        sendCommand.execute();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMessage(@RequestBody int[] msg_ids) {
        // command = new command , command.execute
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
