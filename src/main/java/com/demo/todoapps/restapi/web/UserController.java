package com.demo.todoapps.restapi.web;

import com.demo.todoapps.restapi.core.application.UserService;
import com.demo.todoapps.restapi.core.domain.User;
import com.demo.todoapps.restapi.core.domain.UserResponse;
import com.demo.todoapps.restapi.core.domain.WriteUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id , @RequestBody WriteUser user){
        boolean update = userService.update(id, user);
        return update ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id ){
        boolean deleted = userService.deleteUser(id);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> searchUser(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(userService.findByUser(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> userAll(){
       return ResponseEntity.ok(userService.findAllUsers());
    }




}
