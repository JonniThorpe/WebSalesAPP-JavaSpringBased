package com.theenglishcut.service;

import com.theenglishcut.dao.ProductoACategoriaRepository;
import com.theenglishcut.dto.ProductToCategory;
import com.theenglishcut.entity.ProductToCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductToCategoryService extends DTOService<ProductToCategory, ProductToCategoryEntity>{
    @Autowired
    protected ProductoACategoriaRepository productoACategoriaRepository;

    public void deleteByProductId(Integer id){
        this.productoACategoriaRepository.deleteByProductID(id);
    }
}
