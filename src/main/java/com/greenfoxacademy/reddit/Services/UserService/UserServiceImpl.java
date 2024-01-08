package com.greenfoxacademy.reddit.Services.UserService;

import com.greenfoxacademy.reddit.Models.User;
import com.greenfoxacademy.reddit.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signIn(String username, String password, User user, HttpSession session) {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser == null) {
            throw new IllegalArgumentException("Username doesn't exist.");
        }

        if (!existingUser.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password doesn't match.");
        }

        session.setAttribute("username", username);
    }

    @Override
    public void register(String username, String password, User user, HttpSession session) {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        userRepository.save(user);
        session.setAttribute("username", username);
    }
}