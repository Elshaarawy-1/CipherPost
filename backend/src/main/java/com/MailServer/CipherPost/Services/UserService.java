package com.MailServer.CipherPost.Services;

import org.springframework.beans.factory.annotation.Autowired;
import com.MailServer.CipherPost.repositories.UserRepository;
import com.MailServer.CipherPost.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void saveUser(User user) {
        userRepository.save(user);
    }
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
