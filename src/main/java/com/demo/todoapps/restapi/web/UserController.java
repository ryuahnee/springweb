package com.demo.todoapps.restapi.web;

import com.demo.todoapps.restapi.core.application.UserService;
import com.demo.todoapps.restapi.core.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.saveUser(user));
    }

    @PutMapping("{/id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id , @RequestBody User user){
        boolean update = userService.update(id, user);
        return update ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser( @RequestBody User user){
        boolean deleted = userService.deleteUser(user);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> searchUser(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> userAll(){
       return ResponseEntity.ok(userService.findAllUsers());
    }

}
