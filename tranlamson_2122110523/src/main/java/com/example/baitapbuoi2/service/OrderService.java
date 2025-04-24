package com.example.baitapbuoi2.service;

import com.example.baitapbuoi2.config.OrderConfig;
import com.example.baitapbuoi2.dto.OrderDTO;
import com.example.baitapbuoi2.repository.OrderRepository;
import com.example.baitapbuoi2.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private OrderConfig orderConfig;

    private static final String ORDER_KEY_PREFIX = "order:";

    public List<OrderDTO> addOrder(List<OrderDTO> orders) {
        if (orderRepository.getAllOrders().size() + orders.size() > orderConfig.getMaxOrders()) {
            throw new RuntimeException("Maximum order limit exceeded");
        }
        List<OrderDTO> savedOrders = orders.stream().map(orderRepository::addOrder).toList();
        savedOrders.forEach(order -> redisTemplate.opsForValue().set(ORDER_KEY_PREFIX + order.getId(), order, 1, TimeUnit.HOURS));
        return savedOrders;
    }

    public OrderDTO getOrderById(Long id) {
        OrderDTO order = (OrderDTO) redisTemplate.opsForValue().get(ORDER_KEY_PREFIX + id);
        if (order == null) {
            order = orderRepository.getOrderById(id).orElseThrow(() -> new OrderNotFoundException(id));
            redisTemplate.opsForValue().set(ORDER_KEY_PREFIX + id, order, 1, TimeUnit.HOURS);
        }
        return order;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.getOrderById(id).isPresent()) {
            throw new OrderNotFoundException(id);
        }
        orderRepository.deleteOrder(id);
        redisTemplate.delete(ORDER_KEY_PREFIX + id);
    }

    public OrderDTO updateOrder(Long id, OrderDTO updatedOrder) {
        OrderDTO order = orderRepository.updateOrder(id, updatedOrder);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }
        redisTemplate.opsForValue().set(ORDER_KEY_PREFIX + id, order, 1, TimeUnit.HOURS);
        return order;
    }
}