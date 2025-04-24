package com.example.baitapbuoi2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    private Long id;
    private String itemName;
    private int quantity;
    private double price;
    private double totalPrice;

    public void calculateTotalPrice() {
        totalPrice = quantity * price;
        if (quantity > 50) {
            totalPrice *= 0.95; // Giảm 5%
        }
    }
}