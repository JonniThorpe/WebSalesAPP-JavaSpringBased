package com.theenglishcut.entity;

import com.theenglishcut.dto.DTO;
import com.theenglishcut.dto.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductEntity implements DTO<Product> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "Inventario")
    private StockEntity inventario;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private List<ProductToOrderEntity> pedidos;

    @OneToMany(mappedBy = "producto")
    private List<ProductToCategoryEntity> categorias;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public StockEntity getInventario() {
        return inventario;
    }

    public void setInventario(StockEntity inventario) {
        this.inventario = inventario;
    }

    public List<ProductToOrderEntity> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<ProductToOrderEntity> pedidos) {
        this.pedidos = pedidos;
    }

    public List<ProductToCategoryEntity> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<ProductToCategoryEntity> categorias) {
        this.categorias = categorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Product toDTO(){
        Product product = new Product();
        product.setId(this.ID);
        product.setName(this.nombre);
        product.setDescription(this.descripcion);
        product.setPrice(this.precio);
        product.setImage(this.imagen);
        product.setStock(this.inventario.toDTO());

        List<Integer> ordersList = new ArrayList<Integer>();
        this.pedidos.forEach(
                (final ProductToOrderEntity productToOrder)->
                        ordersList.add(productToOrder.getPedido().getID())
        );
        product.setOrders(ordersList);
        List<Integer> categoryList = new ArrayList<Integer>();
        this.categorias.forEach(
                (final ProductToCategoryEntity productToCategory)->
                        categoryList.add(productToCategory.getCategoria().getID())
        );
        product.setCategories(categoryList);

        return product;

    }

}

