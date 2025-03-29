package com.demo.todoapps.restapi.core.application;

import com.demo.todoapps.restapi.core.domain.User;
import com.demo.todoapps.restapi.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServicelmp {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean update(Long id, User user) {
        if(userRepository.existsById(Math.toIntExact(user.getId()))){
            User updateuser = user.update(user.getName(),user.getEmail());
            userRepository.save(updateuser);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        boolean value = userRepository.existsById(Math.toIntExact(user.getId()));
        if(userRepository.existsById(Math.toIntExact(user.getId()))){
            userRepository.delete(findUserById(user.getId()));
            return value;
        }
        return value;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId);
    }


}
