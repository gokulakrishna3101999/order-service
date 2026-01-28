package com.krishna.order.service;

import com.krishna.order.dto.OrderClientDTO;
import com.krishna.order.dto.OrderDTO;
import com.krishna.order.dto.UserDTO;
import com.krishna.order.entity.Order;
import com.krishna.order.mapper.OrderMapper;
import com.krishna.order.repository.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    @Mock
    private OrderRepo orderRepo;

    @Mock
    private SequenceGenerator sequenceGenerator;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrderInDb_shouldSaveOrderAndReturnOrderDTO() {
        // Arrange
        OrderClientDTO orderDetails = new OrderClientDTO();
        Integer newOrderId = 1;
        UserDTO userDTO = new UserDTO();
        Order orderToBeSaved = new Order(newOrderId, orderDetails.getFoodItemList(), userDTO, orderDetails.getRestaurant());
        OrderDTO orderDTOExpected = orderMapper.mapOrderToOrderDTO(orderToBeSaved);
        when(sequenceGenerator.getNextOrderId()).thenReturn(newOrderId);
        when(restTemplate.getForObject(anyString(), eq(UserDTO.class))).thenReturn(userDTO);
        when(orderRepo.save(orderToBeSaved)).thenReturn(orderToBeSaved);
        // Act
        OrderDTO orderDTOActual = orderService.saveOrder(orderDetails);
        // Assert
        verify(sequenceGenerator, times(1)).getNextOrderId();
        assertDoesNotThrow(() -> orderService.saveOrder(orderDetails));
    }
}
