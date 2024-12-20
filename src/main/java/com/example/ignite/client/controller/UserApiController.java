package com.example.ignite.client.controller;

import com.example.ignite.client.dto.UserDTO;
import com.example.ignite.server.entity.User;
import com.example.ignite.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
        * REST Controller to handle requests.
 */
@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<User> getUsers(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // User Endpoints
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
