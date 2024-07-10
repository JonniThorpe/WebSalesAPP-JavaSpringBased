package com.theenglishcut.entity;

import com.theenglishcut.dto.Stock;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private Integer cantidad;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<ProductEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductEntity> productos) {
        this.productos = productos;
    }

    @OneToMany(mappedBy = "inventario")
    private List<ProductEntity> productos;

    // getters and setters

    public Stock toDTO(){
        Stock stock = new Stock();
        stock.setId(this.ID);
        stock.setQuantity(this.cantidad);
        List<Integer> productList = new ArrayList<Integer>();
        this.productos.forEach((final ProductEntity product) -> productList.add(product.getID()));
        stock.setProducts(productList);
        return stock;
    }
}

