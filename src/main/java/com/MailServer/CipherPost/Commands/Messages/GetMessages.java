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

import com.MailServer.CipherPost.Facades.ContactFacade;
import com.MailServer.CipherPost.Facades.FolderFacade;
import com.MailServer.CipherPost.Facades.MessageFacade;
import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.User;

import java.util.List;

public class GetMessages implements MessageCommand<List<FolderMessage>>{
    private final FolderFacade folderFacade;
    private final Folder folder;
    private final String contentSearch;
    private final String sortField;

//    public GetMessages(FolderFacade folderFacade, Folder folder) {
//        this.folderFacade = folderFacade;
//        this.folder = folder;
//    }

    public GetMessages(FolderFacade folderFacade, Folder folder, String contentSearch, String sortField) {
        this.folderFacade = folderFacade;
        this.folder = folder;
        this.contentSearch = contentSearch;
        this.sortField = sortField;


    }

    public List<FolderMessage> execute()
    {
        return folderFacade.getMessages(folder, contentSearch, sortField);

    }
}
