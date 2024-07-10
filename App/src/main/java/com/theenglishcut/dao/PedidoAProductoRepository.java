package com.theenglishcut.dao;

import com.theenglishcut.entity.ProductToCategoryEntity;
import com.theenglishcut.entity.ProductToOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoAProductoRepository extends JpaRepository<ProductToOrderEntity, Integer> {
    @Query("select pc from ProductToOrderEntity pc where pc.producto.ID = :id")
    public List<ProductToOrderEntity> findProductToOrderByProductID(Integer id);

}
