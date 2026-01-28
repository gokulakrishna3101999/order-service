package com.krishna.order.controller;

import com.krishna.order.dto.OrderClientDTO;
import com.krishna.order.dto.OrderDTO;
import com.krishna.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrder_shouldReturnCreatedStatus() {
        // Arrange
        OrderClientDTO orderDetails = new OrderClientDTO();
        OrderDTO orderSavedInDB = new OrderDTO();
        when(orderService.saveOrder(orderDetails)).thenReturn(orderSavedInDB);

        // Act
        ResponseEntity<OrderDTO> response = orderController.saveOrder(orderDetails);

        // Assert
        verify(orderService, times(1)).saveOrder(orderDetails);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderSavedInDB, response.getBody());
    }
}
