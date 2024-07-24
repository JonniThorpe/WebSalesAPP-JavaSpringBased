package com.theenglishcut.entity;

import com.theenglishcut.dto.DTO;
import com.theenglishcut.dto.Rol;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RolEntity implements DTO<Rol> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String nombre;

    @OneToMany(mappedBy = "rol")
    private List<UserEntity> usuarios;

    public List<UserEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UserEntity> usuarios) {
        this.usuarios = usuarios;
    }

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

    @Override
    public Rol toDTO() {
        Rol rol = new Rol();
        rol.setId(this.ID);
        rol.setName(this.nombre);
        List<Integer> usersList = new ArrayList<Integer>();
        this.usuarios.forEach(
                (final UserEntity userEntity)->
                        usersList.add(userEntity.getID())
        );
        rol.setUsers(usersList);
        return rol;

    }


    // getters and setters
}
