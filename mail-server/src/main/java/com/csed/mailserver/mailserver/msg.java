package com.csed.mailserver.mailserver;

public class msg {
    public user sender;
    public user receiver;
    public String subject;
    public String content;
    public int time;
    public int msg_priority;
    public String[] attachements;
    
    msg(msgDTO msg)
    {
        this.sender = msg.sender;
        this.receiver = msg.receiver;
        this.subject= msg.subject;
        this.content = msg.content;
        this.time = msg.time;
        this.msg_priority = msg.msg_priority;
        this.attachements = msg.attachements;
    }
}
