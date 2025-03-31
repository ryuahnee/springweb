package com.demo.todoapps.restapi.core.application;

import com.demo.todoapps.restapi.core.domain.User;
import com.demo.todoapps.restapi.core.domain.UserRequest;
import com.demo.todoapps.restapi.core.domain.UserResponse;
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

    public UserResponse saveUser(UserRequest userRequest) {

        if(userRequest.getId() != 0 && userRepository.findById(userRequest.getId()).isPresent()) {

            User user = User.builder()  // 또는 적절한 생성 방식 사용
                    .name(userRequest.getName())
                    .email(userRequest.getEmail())
                    .build();

            User savedUser = userRepository.save(user);
            return UserResponse.builder()
                    .id(userRequest.getId())
                    .name(userRequest.getName())
                    .email(userRequest.getEmail())
                    .build();
        }


        throw new RuntimeException("이미 존재하는 회원입니다.");
    }

    @Transactional
    public boolean update(Long id, UserRequest userRequest) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            User userUpdate = user.update(userRequest.getName(), userRequest.getEmail());
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
