package com.example.baitapbuoi2.service.impl;

import com.example.baitapbuoi2.dto.UserDTO;
import com.example.baitapbuoi2.dto.UserPostCountDTO;
import com.example.baitapbuoi2.entity.User;
import com.example.baitapbuoi2.exception.UserNotFoundException;
import com.example.baitapbuoi2.repository.UserRepository;
import com.example.baitapbuoi2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                   .map(this::convertToUserDTO)
                   .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                                 .orElseThrow(() -> new UserNotFoundException(id));
        return convertToUserDTO(user);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        User savedUser = userRepository.save(user);
        return convertToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) throws UserNotFoundException {
        User user = userRepository.findById(id)
                                 .orElseThrow(() -> new UserNotFoundException(id));
        user.setName(updatedUserDTO.getName());
        user.setEmail(updatedUserDTO.getEmail());
        user.setAge(updatedUserDTO.getAge());
        User updatedUser = userRepository.save(user);
        return convertToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> findByNameContaining(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        return users.stream()
                   .map(this::convertToUserDTO)
                   .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findAllOrderByName() {
        List<User> users = userRepository.findAllOrderByName();
        return users.stream()
                   .map(this::convertToUserDTO)
                   .collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserWithPosts(Long userId) throws UserNotFoundException {
        User user = userRepository.findUserWithPosts(userId);
        return convertToUserDTO(user);
    }

    @Override
    public List<UserDTO> findUsersWithMoreThan5Posts() {
        List<User> users = userRepository.findUsersWithMoreThan5Posts();
        return users.stream()
                   .map(this::convertToUserDTO)
                   .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findUsersWithNoPosts() {
        List<User> users = userRepository.findUsersWithNoPosts();
        return users.stream()
                   .map(this::convertToUserDTO)
                   .collect(Collectors.toList());
    }

    @Override
    public List<UserPostCountDTO> getUsersWithPostCount() {
    return userRepository.getUsersWithPostCount();
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        return userDTO;
    }

    @Override
    public List<UserDTO> findByCityContaining(String city) {
    List<User> users = userRepository.findByCityContaining(city);
    return users.stream()
               .map(this::convertToUserDTO)
               .collect(Collectors.toList());
    }
}