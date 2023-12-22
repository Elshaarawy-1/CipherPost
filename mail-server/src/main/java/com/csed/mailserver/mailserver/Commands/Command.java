package com.csed.mailserver.mailserver.Commands;

public interface Command<T> {
    T execute();
}
