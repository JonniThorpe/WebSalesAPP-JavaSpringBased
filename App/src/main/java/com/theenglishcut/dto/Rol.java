package com.theenglishcut.dto;

import lombok.Data;

import java.util.List;

@Data
public class Rol {
    private Integer id = -1;
    private String name;
    private List<Integer> users;
}
