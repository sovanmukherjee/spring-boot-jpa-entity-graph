package org.sm.entitygraph.controller;

import lombok.RequiredArgsConstructor;
import org.sm.entitygraph.model.User;
import org.sm.entitygraph.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class UserController implements UserAPI {

    private final UserService userService;

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @Override
    public ResponseEntity<User> getUserById(int userId) {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @Override
    public ResponseEntity<List<User>> getUserByClassName(String className) {
        return ResponseEntity.ok().body(userService.getUserByClassName(className));
    }

    @Override
    public ResponseEntity<List<User>> getUserByRollNumberAndClassName(int rollNumber, Set<String> classNames) {
        return ResponseEntity.ok().body(userService.getUserByRollNumberAndClassName(classNames, rollNumber));
    }
}
