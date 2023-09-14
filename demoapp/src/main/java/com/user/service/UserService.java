package com.user.service;

import com.user.dto.UserDTO;
import com.user.exception.DuplicatedFieldException;
import com.user.exception.UserNotFoundException;
import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO createUser(UserDTO userRequest) {
        if(userRepository.findByEmail(userRequest.getEmail()).isPresent())
            throw new DuplicatedFieldException(userRequest.getEmail());

        return userRepository.save(new User(userRequest)).toDTO();
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO userUpdates) {
        User user = getUser(id);
        return userRepository.save(user.updateValues(userUpdates)).toDTO();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException(id);

        userRepository.deleteById(id);
    }
}

