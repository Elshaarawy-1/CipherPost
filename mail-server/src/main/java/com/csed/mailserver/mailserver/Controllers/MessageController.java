package com.csed.mailserver.mailserver.Controllers;

import com.csed.mailserver.mailserver.DTOs.MessageDTO;
import com.csed.mailserver.mailserver.Clients.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*/api/messages" )
public class MessageController {
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageDTO message) {
        Message sent_msg = new Message(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{msg_id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int msg_id) {
        // command = new command , command.execute
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
