package com.greenfoxacademy.reddit.Services.UserService;

import com.greenfoxacademy.reddit.Models.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface UserService {

    void signIn(String username, String password, User user, HttpSession session);

    void register(String username, String password, User user, HttpSession session);
}