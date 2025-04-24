package com.example.baitapbuoi2.repository;

import org.springframework.stereotype.Repository;

import com.example.baitapbuoi2.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    private final List<OrderDTO> orders = new ArrayList<>();
    private long idCounter = 1;

    public List<OrderDTO> getAllOrders() {
        return orders;
    }

    public OrderDTO addOrder(OrderDTO order) {
        order.setId(idCounter++);
        order.calculateTotalPrice();
        orders.add(order);
        return order;
    }

    public Optional<OrderDTO> getOrderById(Long id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    public void deleteOrder(Long id) {
        orders.removeIf(order -> order.getId().equals(id));
    }

    public OrderDTO updateOrder(Long id, OrderDTO updatedOrder) {
        Optional<OrderDTO> existingOrder = getOrderById(id);
        if (existingOrder.isPresent()) {
            OrderDTO order = existingOrder.get();
            order.setItemName(updatedOrder.getItemName());
            order.setQuantity(updatedOrder.getQuantity());
            order.setPrice(updatedOrder.getPrice());
            order.calculateTotalPrice(); // Tính lại tổng giá trị
            return order;
        }
        return null;
    }
}