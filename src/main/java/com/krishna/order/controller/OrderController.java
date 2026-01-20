package com.krishna.order.controller;

import com.krishna.order.dto.OrderClientDTO;
import com.krishna.order.dto.OrderDTO;
import com.krishna.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderClientDTO orderClientDTO) {
        return new ResponseEntity<>(orderService.saveOrder(orderClientDTO), HttpStatus.CREATED);
    }
}
