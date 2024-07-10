package com.theenglishcut.dto;


import lombok.Data;

import java.util.List;

@Data
public class Stock {
    private Integer id;
    private Integer Quantity;
    private List<Integer> products;
}
