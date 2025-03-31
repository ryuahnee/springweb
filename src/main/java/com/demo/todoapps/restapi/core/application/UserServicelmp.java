package com.demo.todoapps.restapi.core.application;

import com.demo.todoapps.restapi.core.domain.User;
import com.demo.todoapps.restapi.data.UserRepository;

import java.util.List;
import java.util.Optional;

public interface UserServicelmp {

    User saveUser(User user);
    boolean update(Long id, User user);
    boolean deleteUser(Long id);
    List<User> findAllUsers();
    User findUserById(Long userId);



}
