package com.theenglishcut.service;

import com.theenglishcut.dao.UsuarioRepository;
import com.theenglishcut.dto.User;
import com.theenglishcut.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends DTOService<User, UserEntity> {
    @Autowired
    protected UsuarioRepository usuarioRepository;

    public User findByUsername(String username) {
        return this.usuarioRepository.findByNombreUser(username).toDTO();
    }

    public List<User> findAll(){
        return this.entidadesADTO(usuarioRepository.findAll());
    }
}
