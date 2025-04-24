package com.example.baitapbuoi2.service;

import com.example.baitapbuoi2.dto.UserDTO;
import com.example.baitapbuoi2.dto.UserPostCountDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO updatedUserDTO);
    void deleteUser(Long id);
    List<UserDTO> findByNameContaining(String keyword);
    List<UserDTO> findByCityContaining(String city);
    List<UserDTO> findAllOrderByName();
    UserDTO findUserWithPosts(Long userId);
    List<UserDTO> findUsersWithMoreThan5Posts();
    List<UserDTO> findUsersWithNoPosts();
    List<UserPostCountDTO> getUsersWithPostCount();
}