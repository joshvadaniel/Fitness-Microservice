package com.fitness.userservice.service;

import com.fitness.userservice.dto.RequestDTO;
import com.fitness.userservice.dto.ResponseDTO;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseDTO register(RequestDTO requestDTO) {
        if(userRepository.existsByEmail(requestDTO.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());

        User savedUser = userRepository.save(user);
        ResponseDTO userResponseDTO = new ResponseDTO();
        userResponseDTO.setId(savedUser.getId());
        userResponseDTO.setEmail(savedUser.getEmail());
        userResponseDTO.setPassword(savedUser.getPassword());
        userResponseDTO.setFirstName(savedUser.getFirstName());
        userResponseDTO.setLastName(savedUser.getLastName());
        userResponseDTO.setCreatedAt(savedUser.getCreatedAt());
        userResponseDTO.setUpdatedAt(savedUser.getUpdatedAt());
        return userResponseDTO;
    }

    public ResponseDTO getUserProfile(String userId) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ResponseDTO userResponseDTO = new ResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setPassword(user.getPassword());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setCreatedAt(user.getCreatedAt());
        userResponseDTO.setUpdatedAt(user.getUpdatedAt());
        return userResponseDTO;

    }

    public Boolean existsByUserId(String userId) {
        return userRepository.existsById(userId);
    }
}
