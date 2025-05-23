package com.example.baitapbuoi2.exception;

public class OrderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(Long id) {
        super("Order with ID " + id + " not found");
    }
}