package com.krishna.order.entity;

import com.krishna.order.dto.FoodItemDTO;
import com.krishna.order.dto.RestaurantDTO;
import com.krishna.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order {
    private Integer orderId;
    private List<FoodItemDTO> foodItems;
    private UserDTO user;
    private RestaurantDTO restaurant;
}
