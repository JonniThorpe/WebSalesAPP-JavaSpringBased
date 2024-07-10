package com.theenglishcut.dao;

import com.theenglishcut.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductoRepository extends JpaRepository<ProductEntity, Integer> {

    @Query("SELECT p FROM ProductToCategoryEntity pc JOIN pc.producto p WHERE pc.category.ID = :categoryID")
    List<ProductEntity> findByCategoryID(@Param("categoryID") Integer categoryID);
}
