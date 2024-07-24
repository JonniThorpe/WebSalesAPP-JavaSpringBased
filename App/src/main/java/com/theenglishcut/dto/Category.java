package com.theenglishcut.dto;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private Integer id=-1;
    private String name;
    private String description;
    private List<Integer> products;
}
