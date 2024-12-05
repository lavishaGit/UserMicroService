package com.bank.userms.Controller;

import com.bank.userms.Models.User;
import com.bank.userms.Services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/user")
    public List<User> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User User) {
        return new ResponseEntity<>(userService.addUser(User), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> addUser(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) throws Exception {
        User updatedUser = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id) throws Exception {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    public ResponseEntity<?> respondWithError(Exception e) {
        return new ResponseEntity<>("Exception Occurred. More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
