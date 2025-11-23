package com.example.Library.controller;

import com.example.Library.service.*;
import org.springframework.web.bind.annotation.*;
import com.example.Library.entity.*;
import org.springframework.http.ResponseEntity;
import java.net.URI;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
     public final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> list(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user,@PathVariable Long id){
         return ResponseEntity.ok(userService.updateUser(id,user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User updatedUser){
        User user=userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }
}
