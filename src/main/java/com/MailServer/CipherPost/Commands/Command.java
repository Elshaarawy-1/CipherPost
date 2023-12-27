package com.MailServer.CipherPost.Commands;

public interface Command<T> {
    T execute();
}
