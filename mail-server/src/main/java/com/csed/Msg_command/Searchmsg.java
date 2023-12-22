package com.csed.Msg_command;

import java.util.List;

import com.csed.mailserver.mailserver.msg;

public class Searchmsg implements MsgCommand<List<msg>>{
    private String criteria;
    private String searchinput;

    //!! private MessageFacade msg_facade;
    public List<msg> execute()
    {
        return null; //will be removed
        //!!return msg_facade.searchBy(criteria,searchinput);
    }
    
}
