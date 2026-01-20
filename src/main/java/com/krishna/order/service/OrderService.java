package com.krishna.order.service;

import com.krishna.order.dto.OrderClientDTO;
import com.krishna.order.dto.OrderDTO;
import com.krishna.order.dto.UserDTO;
import com.krishna.order.entity.Order;
import com.krishna.order.mapper.OrderMapper;
import com.krishna.order.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final RestTemplate restTemplate;
    private final SequenceGenerator sequenceGenerator;
    private final OrderMapper orderMapper;

    public OrderDTO saveOrder(OrderClientDTO orderClientDTO) {
        Order order = new Order();
        order.setOrderId(sequenceGenerator.getNextOrderId());
        order.setFoodItems(orderClientDTO.getFoodItemList());
        order.setRestaurant(orderClientDTO.getRestaurant());
        order.setUser(getUser(orderClientDTO.getUserId()));
        orderRepo.save(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    private UserDTO getUser(Integer userId) {
        return this.restTemplate.getForObject("http://userinfo/users/get/"+userId,UserDTO.class);
    }
}
