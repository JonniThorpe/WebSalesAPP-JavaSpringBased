package com.theenglishcut.entity;
import com.theenglishcut.dto.DTO;
import com.theenglishcut.dto.ProductToCategory;
import jakarta.persistence.*;

@Entity
public class ProductToCategoryEntity implements DTO<ProductToCategory> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity producto;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public Integer getId() {
        return ID;
    }

    public void setId(Integer id) {
        this.ID = id;
    }

    public ProductEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductEntity producto) {
        this.producto = producto;
    }

    public CategoryEntity getCategoria() {
        return category;
    }

    public void setCategoria(CategoryEntity category) {
        this.category = category;
    }

    @Override
    public ProductToCategory toDTO() {
        return null;
    }
}

