package com.petstagram.service;

import com.petstagram.model.entity.User;

public interface AuthService {
    void singUp(String name, String email, String password);
    User login(String email, String password);
    void leave(Long userId);
}
