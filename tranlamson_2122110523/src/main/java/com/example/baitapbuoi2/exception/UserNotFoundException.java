package com.example.baitapbuoi2.exception;


public class UserNotFoundException extends RuntimeException {
    /**
     * Xử lý khi người dùng không tồn tại
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super("User with ID " + id + " not found");
    }
}
