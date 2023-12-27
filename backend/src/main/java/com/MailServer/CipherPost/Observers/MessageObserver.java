package com.MailServer.CipherPost.Observers;

import com.MailServer.CipherPost.entities.Message;

public interface MessageObserver {
    void update(Message message);
}
