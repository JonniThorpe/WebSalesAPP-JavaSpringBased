package com.theenglishcut.dto;

import java.util.ArrayList;
import java.util.List;

public interface DTO<DTOClass> {
    DTOClass toDTO ();

    static <T extends DTO<U>, U> List<U> toDTOList(Iterable<T> original) {
        List<U> dtos = new ArrayList<>();
        for (T entidad : original) {
            dtos.add(entidad.toDTO());
        }
        return dtos;
    }
}
