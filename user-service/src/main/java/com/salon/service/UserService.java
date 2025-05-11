package com.salon.service;

import com.salon.domain.User;
import com.salon.dto.UserDTO;
import com.salon.exception.UserException;
import com.salon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws UserException {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserException("Failed to create user: " + e.getMessage());
        }
    }

    public List<User> getUsers() throws UserException {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new UserException("Failed to fetch users: " + e.getMessage());
        }
    }

    public User getUserById(Long id) throws UserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));
    }

    public User updateUser(Long userId, UserDTO userDTO) throws UserException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found with id: " + userId));

        if (userDTO.getFullName() != null) user.setFullName(userDTO.getFullName());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getPhone() != null) user.setPhone(userDTO.getPhone());
        if (userDTO.getRole() != null) user.setRole(userDTO.getRole());

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserException("Failed to update user: " + e.getMessage());
        }
    }

    public void deleteUser(Long id) throws UserException {
        if (!userRepository.existsById(id)) {
            throw new UserException("User not found with ID: " + id);
        }
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserException("Failed to delete user: " + e.getMessage());
        }
    }
}
