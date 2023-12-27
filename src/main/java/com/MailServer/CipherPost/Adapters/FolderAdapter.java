package com.MailServer.CipherPost.Adapters;

import com.MailServer.CipherPost.DTOs.FolderDTO;
import com.MailServer.CipherPost.entities.Folder;

public class FolderAdapter implements Adapter<Folder, FolderDTO> {
    @Override
    public FolderDTO toDto(Folder source) {
        FolderDTO dto = new FolderDTO();
        dto.setFolder_id(source.getId());
        dto.setFolder_name(source.getFolderName());
        return dto;
    }
}
