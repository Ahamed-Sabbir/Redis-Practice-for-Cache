package com.sabbir.controller;

import com.sabbir.model.User;
import com.sabbir.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        HashMap<String, Object> response = new HashMap<>();
        User user = userService.getUserByEmail(email);
        if (user != null) response.put("user", user);
        else response.put("user", "User not found");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<?> findById(@PathVariable Long Id) {
        HashMap<String, Object> response = new HashMap<>();
        User user = userService.getUserById(Id);
        if (user != null) response.put("user", user);
        else response.put("user", "User not found");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        HashMap<String, Object> response = new HashMap<>();
        User savedUser = userService.save(user);
        response.put("user", savedUser);
        response.put("message", "user saved");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id) {
        HashMap<String, Object> response = new HashMap<>();
        User user = userService.getUserById(Id);
        if (user != null) {
            userService.delete(user);
            response.put("message", "user removed");
        }
        else response.put("user", "User not found");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
