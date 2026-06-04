package com.pfe.controller;


import com.pfe.dto.UserUpdateRequest;
import com.pfe.model.Role;
import com.pfe.model.User;
import com.pfe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id,
                                       @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> findByRole(@PathVariable Role role) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findByRole(role));
    }

}