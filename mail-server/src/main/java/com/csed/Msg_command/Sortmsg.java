package com.csed.Msg_command;

import java.util.List;

import com.csed.mailserver.mailserver.msg;

public class Sortmsg implements MsgCommand<List<msg>>{
    private String criteria;
   //!! private MessageFacade msg_facade;

    
    //!! private MessageFacade msg_facade;
    public List<msg> execute()
    {
        return null;
        //!!return msg_facade.sortBy(criteria);
    }
}
