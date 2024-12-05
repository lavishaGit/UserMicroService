package com.bank.userms.Services;

import com.bank.userms.Models.User;
import com.bank.userms.Repositories.UserRepository;
import com.bank.userms.Services.Interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new Exception("User not found" + userId));

    }

    @Override
    public User updateUser(Long userId, User user) throws Exception {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found" + userId));
        // Update the user's fields with the new values
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(existingUser);
    }


    @Override
    public void deleteUserById(Long userId) throws Exception {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found" + userId));

        userRepository.deleteById(userId);

    }


}
