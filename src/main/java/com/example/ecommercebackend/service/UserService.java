package com.example.ecommercebackend.service;

import com.example.ecommercebackend.dao.UserRepository;
import com.example.ecommercebackend.exception.ResourceNotFoundException;
import com.example.ecommercebackend.model.User;
import org.springframework.stereotype.Service;

@Service @Deprecated
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUser(int userId) throws ResourceNotFoundException {

    return userRepository.findById(userId).orElseThrow(
            ()->new ResourceNotFoundException("User with id " +userId+ " not found.")
    );
    }
}
