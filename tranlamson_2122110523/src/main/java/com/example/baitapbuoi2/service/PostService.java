package com.example.baitapbuoi2.service;

import com.example.baitapbuoi2.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getPostsByUserId(Long userId);
    List<PostDTO> getPostsByTitleContaining(String keyword);
    List<PostDTO> getPostsByKeyword(String keyword);
    PostDTO getLatestPostByUser(Long userId);
    PostDTO addPost(PostDTO postDTO);
    PostDTO updatePost(Long id, PostDTO postDTO);
    void deletePost(Long id);
    List<PostDTO> findPostsByUserIdAndTitleContaining(Long userId, String keyword);
}