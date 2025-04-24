package com.example.baitapbuoi2.repository;

import com.example.baitapbuoi2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findPostsByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    List<Post> findPostsByTitleContaining(@Param("keyword") String keyword);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    List<Post> findPostsByKeyword(@Param("keyword") String keyword);

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.title LIKE %:keyword%")
    List<Post> findPostsByUserIdAndTitleContaining(@Param("userId") Long userId, @Param("keyword") String keyword);

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId ORDER BY p.id DESC LIMIT 1")
    Post findLatestPostByUser(@Param("userId") Long userId);
}