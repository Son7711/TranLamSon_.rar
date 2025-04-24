package com.example.baitapbuoi2.dto;

public class UserPostCountDTO {
    private Long userId;
    private int postCount;

    // Constructors, Getters, and Setters
    public UserPostCountDTO() {}

    public UserPostCountDTO(Long userId, int postCount) {
        this.userId = userId;
        this.postCount = postCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
}