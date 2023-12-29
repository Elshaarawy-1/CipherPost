package com.MailServer.CipherPost.Adapters;

import com.MailServer.CipherPost.DTOs.ContactDTO;
import com.MailServer.CipherPost.DTOs.FolderDTO;
import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.repositories.FolderMessagesRepository;
import com.MailServer.CipherPost.repositories.FolderRepository;

import java.util.LinkedList;
import java.util.List;

public class FolderAdapter implements Adapter<Folder, FolderDTO> {
    FolderMessagesRepository folderMessagesRepository;
    @Override
    public FolderDTO toDto(Folder source) {
        FolderDTO dto = new FolderDTO();
        dto.setFolder_id(source.getId());
        dto.setFolder_name(source.getFolderName());
//        dto.setFolder_messages(folderMessagesRepository.findByFolder(source).getSize());
        return dto;
    }
    public List<FolderDTO> toListDTO(List<Folder> folders) {
        List<FolderDTO> folders_dto = new LinkedList<>();
        for (Folder folder : folders){
            folders_dto.add(this.toDto(folder));
        }
        return folders_dto;
    }
}
