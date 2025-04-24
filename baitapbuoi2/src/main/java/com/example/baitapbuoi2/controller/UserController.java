package com.example.baitapbuoi2.controller;

import com.example.baitapbuoi2.dto.UserDTO;
import com.example.baitapbuoi2.dto.UserPostCountDTO;
import com.example.baitapbuoi2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Thêm user
    @PostMapping 
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUserDTO = userService.addUser(userDTO);
        return ResponseEntity.ok(savedUserDTO);
    }
    // Lấy all danh sách User
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = userService.getAllUsers();
        return ResponseEntity.ok(userDTOs);
    }
    // Lấy User theo ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    // Cập nhật User
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        UserDTO userDTO = userService.updateUser(id, updatedUserDTO);
        return ResponseEntity.ok(userDTO);
    }
    // Xóa User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    // Tìm kiếm User theo tên chứa từ khóa
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam String keyword) {
        List<UserDTO> userDTOs = userService.findByNameContaining(keyword);
        return ResponseEntity.ok(userDTOs);
    }
    // Sắp xếp danh sách Users theo tên
    @GetMapping("/sorted")
    public ResponseEntity<List<UserDTO>> getUsersSortedByName() {
        List<UserDTO> userDTOs = userService.findAllOrderByName();
        return ResponseEntity.ok(userDTOs);
    }
    // Lấy danh sách các bài Post của User theo id
    @GetMapping("/{id}/posts")
    public ResponseEntity<UserDTO> getUserWithPosts(@PathVariable Long id) {
        UserDTO userDTO = userService.findUserWithPosts(id);
        return ResponseEntity.ok(userDTO);
    }
    // Lấy danh sách User có hơn 5 bài viết
    @GetMapping("/more-than-5-posts")
    public ResponseEntity<List<UserDTO>> getUsersWithMoreThan5Posts() {
        List<UserDTO> userDTOs = userService.findUsersWithMoreThan5Posts();
        return ResponseEntity.ok(userDTOs);
    }
    // Truy vấn User chưa đăng bài viết nào
    @GetMapping("/no-posts")
    public ResponseEntity<List<UserDTO>> getUsersWithNoPosts() {
        List<UserDTO> userDTOs = userService.findUsersWithNoPosts();
        return ResponseEntity.ok(userDTOs);
    }
    // Lấy danh sách User và số lượng bài viết của từng người
    @GetMapping("/with-post-count")
    public ResponseEntity<List<UserPostCountDTO>> getUsersWithPostCount() {
    List<UserPostCountDTO> usersWithPostCount = userService.getUsersWithPostCount();
    return ResponseEntity.ok(usersWithPostCount);
    }
    // Tìm kiếm danh sách user theo city chứa từ khóa
    @GetMapping("/search/city")
    public ResponseEntity<List<UserDTO>> searchUsersByCity(@RequestParam String city) {
    List<UserDTO> userDTOs = userService.findByCityContaining(city);
    return ResponseEntity.ok(userDTOs);
}
}