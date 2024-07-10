package com.theenglishcut.dto;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private Integer id =-1;
    private String name;
    private String description;
    private double price;
    private String image;
    //inventario
    private Stock stock;
    private List<Integer> categories;
    private List<Integer> orders;
}
