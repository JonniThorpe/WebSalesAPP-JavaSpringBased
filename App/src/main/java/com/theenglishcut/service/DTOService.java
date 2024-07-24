package com.theenglishcut.service;

import com.theenglishcut.dto.DTO;

import java.util.ArrayList;
import java.util.List;

public abstract class DTOService<DTOClass, EntityClass extends DTO<DTOClass>> {

    protected List<DTOClass> entidadesADTO(List<EntityClass> entidades) {
        List<DTOClass> lista = new ArrayList<>();
        for (EntityClass entidad : entidades) {
            lista.add(entidad.toDTO());
        }
        return lista;
    }
}
