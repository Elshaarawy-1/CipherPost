package com.MailServer.CipherPost.Services;

import com.MailServer.CipherPost.entities.Contact;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.User;
import com.MailServer.CipherPost.repositories.ContactRepository;
import com.MailServer.CipherPost.repositories.FolderRepository;
import com.MailServer.CipherPost.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FolderRepository folderRepository;
    public void saveUser(User user) {
        userRepository.save(user);
        generateDefaultFoldersForUser(user);
    }
    public void generateDefaultFoldersForUser(User user) {
        List<Folder> folders = new ArrayList<>();
        folders.add(new Folder(user, "inbox"));
        folders.add(new Folder(user, "sent"));
        folders.add(new Folder(user, "draft"));
        folders.add(new Folder(user, "trash"));
        folderRepository.saveAll(folders);
    }
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseGet(() -> userRepository.findById(id).orElse(null));
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
