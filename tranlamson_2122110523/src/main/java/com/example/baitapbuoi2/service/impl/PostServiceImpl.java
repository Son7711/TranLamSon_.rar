package com.example.baitapbuoi2.service.impl;

import com.example.baitapbuoi2.dto.PostDTO;
import com.example.baitapbuoi2.entity.Post;
import com.example.baitapbuoi2.entity.User;
import com.example.baitapbuoi2.exception.UserNotFoundException;
import com.example.baitapbuoi2.repository.PostRepository;
import com.example.baitapbuoi2.repository.UserRepository;
import com.example.baitapbuoi2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PostDTO> getPostsByUserId(Long userId) {
        List<Post> posts = postRepository.findPostsByUserId(userId);
        return posts.stream()
                    .map(this::convertToPostDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostsByTitleContaining(String keyword) {
        List<Post> posts = postRepository.findPostsByTitleContaining(keyword);
        return posts.stream()
                    .map(this::convertToPostDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostsByKeyword(String keyword) {
        List<Post> posts = postRepository.findPostsByKeyword(keyword);
        return posts.stream()
                    .map(this::convertToPostDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public PostDTO getLatestPostByUser(Long userId) {
        Post post = postRepository.findLatestPostByUser(userId);
        return convertToPostDTO(post);
    }

    @Override
    public PostDTO addPost(PostDTO postDTO) {
        User user = userRepository.findById(postDTO.getUserId())
                                 .orElseThrow(() -> new UserNotFoundException(postDTO.getUserId()));
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        return convertToPostDTO(savedPost);
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        Post updatedPost = postRepository.save(post);
        return convertToPostDTO(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private PostDTO convertToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUserId(post.getUser().getId());
        return postDTO;
    }
    @Override
    public List<PostDTO> findPostsByUserIdAndTitleContaining(Long userId, String keyword) {
    List<Post> posts = postRepository.findPostsByUserIdAndTitleContaining(userId, keyword);
    return posts.stream()
                .map(this::convertToPostDTO)
                .collect(Collectors.toList());
}
}