package com.demo.todoapps.restapi.core.application;

import com.demo.todoapps.restapi.core.domain.User;
import com.demo.todoapps.restapi.core.domain.UserResponse;
import com.demo.todoapps.restapi.core.domain.WriteUser;
import com.demo.todoapps.restapi.data.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService  {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse saveUser(User user) {

        if(user.getId() != 0 && userRepository.findById(user.getId()).isPresent()) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }
        User saveUser = userRepository.save(user);
        return UserResponse.builder()
                .id(saveUser.getId())
                .name(saveUser.getName())
                .email(saveUser.getEmail())
                .build();
    }

    @Transactional
    public boolean update(Long id, WriteUser writeuser) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            User userUpdate = user.update(writeuser.getName(), writeuser.getEmail());
            userRepository.save(userUpdate);
            return true;
        }

        return false;
    }


    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        })
        .orElseThrow(() -> new RuntimeException("존재 하지 않는 회원 입니다"));
    }

    public List<UserResponse> findAllUsers() {
        List<User> all = userRepository.findAll();
        return convertToResponse(all);

    }

    public UserResponse findByUser(Long userId) {
        Optional<User> userEntity = userRepository.findById(userId);

        if (userEntity.isPresent()) {
            return convertToResponse(userEntity.get());
        }
        throw new RuntimeException("등록된 회원이 아닙니다 : " + userId);
    }

    private List<UserResponse> convertToResponse(List<User> users) {
        return users.stream()
                .map(entity ->  UserResponse.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .email(entity.getEmail()).build())
                .collect(Collectors.toList());

    }

    private UserResponse convertToResponse(User userEntity) {
        return UserResponse.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .email(userEntity.getEmail()).build();
    }


}
