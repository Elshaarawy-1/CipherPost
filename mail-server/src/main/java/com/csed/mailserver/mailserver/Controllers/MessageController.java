package com.csed.mailserver.mailserver.Controllers;

import com.csed.mailserver.mailserver.Clients.Message;
import com.csed.mailserver.mailserver.Commands.Command;
import com.csed.mailserver.mailserver.Commands.Messages.ComposeMessage;
import com.csed.mailserver.mailserver.DTOs.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*/api/messages" )
public class MessageController {
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageDTO message) {
        Message sent_msg = new Message(message);
        Command<Void> sendCommand = new ComposeMessage(sent_msg);
        sendCommand.execute();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMessage(@RequestBody int[] msg_ids) {
        // command = new command , command.execute
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
