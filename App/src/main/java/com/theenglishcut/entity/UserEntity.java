package com.theenglishcut.entity;

import com.theenglishcut.dto.DTO;
import com.theenglishcut.dto.User;
import jakarta.persistence.*;

@Entity
public class UserEntity implements DTO<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String nombre;

    private String password;

    @ManyToOne
    @JoinColumn(name = "Rol")
    private RolEntity rol;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }

    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password = password; }

    @Override
    public User toDTO() {
        User user = new User();
        user.setId(this.ID);
        user.setName(this.nombre);
        user.setPassword(this.password);
        user.setRol(this.rol);

        return user;
    }
}

