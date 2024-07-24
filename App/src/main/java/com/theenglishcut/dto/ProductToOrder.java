package com.theenglishcut.dto;

import com.theenglishcut.entity.OrderEntity;
import com.theenglishcut.entity.ProductEntity;
import lombok.Data;

@Data
public class ProductToOrder {
    private ProductEntity product;
    private OrderEntity order;
}
