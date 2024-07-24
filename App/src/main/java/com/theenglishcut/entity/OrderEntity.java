package com.theenglishcut.entity;

import com.theenglishcut.dto.DTO;
import com.theenglishcut.dto.Order;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class OrderEntity implements DTO<Order> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "completada", columnDefinition="BIT(1) default 0")
    private boolean entregaCompletada;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private UserEntity usuario;

    @OneToMany(mappedBy = "pedido",fetch = FetchType.EAGER)
    private List<ProductToOrderEntity> productos;

    private String direccion;

    public List<ProductToOrderEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductToOrderEntity> productos) {
        this.productos = productos;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public boolean isEntregaCompletada() {

        return entregaCompletada;
    }

    public void setEntregaCompletada(boolean entregaCompletada) {
        this.entregaCompletada = entregaCompletada;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public Order toDTO() {
        Order order = new Order();

        order.setId(this.ID);
        order.setCreationDate(this.fechaCreacion);
        order.setCompleted(this.entregaCompletada);
        List<Integer> productList = new ArrayList<Integer>();
        this.productos.forEach(
                (final ProductToOrderEntity productToOrderEntity)->
                        productList.add(productToOrderEntity.getProducto().getID())
        );

        return order;
    }

    // getters and setters
}
