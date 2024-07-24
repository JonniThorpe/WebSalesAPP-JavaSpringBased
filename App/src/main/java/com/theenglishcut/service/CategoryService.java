package com.theenglishcut.service;

import com.theenglishcut.dao.CategoriaRepository;
import com.theenglishcut.dto.Category;
import com.theenglishcut.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends DTOService<Category, CategoryEntity> {
    @Autowired
    protected CategoriaRepository  categoryRepository;

    public List<Category> findAll() {
        List<CategoryEntity>  categoryList = categoryRepository.findAll();
        return this.entidadesADTO(categoryList);
    }

}
