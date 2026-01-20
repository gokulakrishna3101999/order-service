package com.krishna.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderClientDTO {
    List<FoodItemDTO> foodItemList;
    Integer userId;
    RestaurantDTO restaurant;
}
