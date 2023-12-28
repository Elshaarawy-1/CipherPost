//package com.MailServer.CipherPost.Commands.Messages;
//
//import com.MailServer.CipherPost.Facades.MessageFacade;
//import com.MailServer.CipherPost.entities.Folder;
//import com.MailServer.CipherPost.entities.Message;
//import com.MailServer.CipherPost.entities.User;
//
//import java.util.List;
//
//public class GetMessages implements MessageCommand<<List>Message> {
//    private final MessageFacade messageFacade;
//    private final User user;
//
//    public GetMessages(MessageFacade messageFacade, Folder folder) {
//        this.messageFacade = messageFacade;
//        this.user = user;
//    }
//
//    public List<Message> execute()
//    {
//        return messageFacade.getMessages(user);
//}


package com.MailServer.CipherPost.Commands.Messages;

import com.MailServer.CipherPost.Facades.MessageFacade;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class GetMessages implements MessageCommand<Page<FolderMessage>>{
    private final MessageFacade messageFacade;
    private final Folder folder;
    private final String searchField;
    private final String keyword;
    private final Pageable pageable;


    public GetMessages(MessageFacade messageFacade, Folder folder, Pageable pageable, String searchField, String keyword) {
        this.messageFacade = messageFacade;
        this.folder = folder;
        this.pageable = pageable;
        this.searchField = searchField;
        this.keyword = keyword;
    }

    public Page<FolderMessage> execute()
    {
        return messageFacade.getMessages(folder, pageable, searchField, keyword);

    }
}
