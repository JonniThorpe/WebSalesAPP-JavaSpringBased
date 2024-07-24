package com.theenglishcut.dto;

import com.theenglishcut.entity.UserEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private int id = -1;
    private Date creationDate;
    private Boolean completed = false;
    private UserEntity user;
    private List<Integer> products;
    private String address;
}
