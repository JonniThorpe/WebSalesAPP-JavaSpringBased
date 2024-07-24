package com.theenglishcut.dto;

import com.theenglishcut.entity.RolEntity;
import lombok.Data;

@Data
public class User {
    private Integer id =-1;
    private String name;
    private String password;
    private RolEntity rol;
}
