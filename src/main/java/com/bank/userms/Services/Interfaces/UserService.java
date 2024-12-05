package com.bank.userms.Services.Interfaces;

import com.bank.userms.Models.User;

import java.util.List;

public interface UserService {
   User addUser(User user);
    List<User> getAllUsers();
    User getUserById(Long userId) throws Exception;
    User updateUser(Long userId,User user) throws Exception;
void deleteUserById(Long userId) throws Exception;
}
