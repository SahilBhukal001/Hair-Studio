package com.salon.controller;

import com.salon.domain.User;
import com.salon.dto.UserDTO;
import com.salon.exception.UserException;
import com.salon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create/user")
    public ResponseEntity<User> createUser(@RequestBody User user) throws UserException {
        return ResponseEntity.status(201).body(userService.createUser(user));
    }

    @GetMapping("/get/users")
    public ResponseEntity<List<User>> getUsers() throws UserException {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) throws UserException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/update/user/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long userId) throws UserException {
        return ResponseEntity.ok(userService.updateUser(userId, userDTO));
    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) throws UserException {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
