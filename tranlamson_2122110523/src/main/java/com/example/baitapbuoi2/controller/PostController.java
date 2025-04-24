package com.example.baitapbuoi2.controller;

import com.example.baitapbuoi2.dto.PostDTO;
import com.example.baitapbuoi2.entity.Post;
import com.example.baitapbuoi2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    // Lấy danh sách Post của User theo ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostsByUserId(@PathVariable Long userId) {
        List<PostDTO> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }
    // Lấy danh sách các bài Post theo từ khóa title
    @GetMapping("/search/title")
    public ResponseEntity<List<PostDTO>> getPostsByTitleContaining(@RequestParam String keyword) {
        List<PostDTO> posts = postService.getPostsByTitleContaining(keyword);
        return ResponseEntity.ok(posts);
    }
    // Truy vấn danh sách Post có chứa từ khoá trong tiêu đề hoặc nội dung
    @GetMapping("/search/keyword")
    public ResponseEntity<List<PostDTO>> getPostsByKeyword(@RequestParam String keyword) {
        List<PostDTO> posts = postService.getPostsByKeyword(keyword);
        return ResponseEntity.ok(posts);
    }
    // Lấy danh sách bài viết mới nhất của mỗi User
    @GetMapping("/user/{userId}/latest")
    public ResponseEntity<PostDTO> getLatestPostByUser(@PathVariable Long userId) {
        PostDTO post = postService.getLatestPostByUser(userId);
        return ResponseEntity.ok(post);
    }
    // Thêm Post
    @PostMapping
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO) {
        PostDTO savedPost = postService.addPost(postDTO);
        return ResponseEntity.ok(savedPost);
    }
    // Sửa Post
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatePost(id, postDTO);
        return ResponseEntity.ok(updatedPost);
    }
    // Xóa Post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
    // Tìm kiếm danh sách các bài Post của User theo id theo từ khóa title
    @GetMapping("/user/{userId}/search/title")
    public ResponseEntity<List<PostDTO>> getPostsByUserIdAndTitleContaining(
    @PathVariable Long userId, @RequestParam String keyword) {
    List<PostDTO> posts = postService.findPostsByUserIdAndTitleContaining(userId, keyword);
    return ResponseEntity.ok(posts);
}
}