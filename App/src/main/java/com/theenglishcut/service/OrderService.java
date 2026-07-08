package com.theenglishcut.service;

import com.theenglishcut.dao.*;
import com.theenglishcut.dto.Order;
import com.theenglishcut.dto.Product;
import com.theenglishcut.dto.User;
import com.theenglishcut.entity.OrderEntity;
import com.theenglishcut.entity.ProductEntity;
import com.theenglishcut.entity.ProductToOrderEntity;
import com.theenglishcut.entity.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderService extends DTOService<Order, OrderEntity> {
    @Autowired
    protected PedidoRepository pedidoRepository;
    @Autowired
    protected UsuarioRepository usuarioRepository;
    @Autowired
    protected ProductoRepository productoRepository;
    @Autowired
    protected ProductoAPedidoRepository productoAPedidoRepository;
    @Autowired
    protected InventarioRepository inventarioRepository;

    public List<Order> findOrderByUserId(Integer id) {
        return this.entidadesADTO(this.pedidoRepository.findPedidoByUser(id));
    }

    public List<Order> findAllOrders(){
        return this.entidadesADTO(this.pedidoRepository.findAll());
    }

    public void save(User client, Order order, Map<Product,Integer> basketProducts) {
        OrderEntity orderEntity = pedidoRepository.findById(order.getId()).orElse(new OrderEntity());
        orderEntity.setUsuario(usuarioRepository.findById(client.getId()).orElse(null));
        orderEntity.setFechaCreacion(new Date());
        orderEntity.setEntregaCompletada(false);
        orderEntity.setDireccion(order.getAddress());

        pedidoRepository.save(orderEntity);

        List<ProductToOrderEntity> productoaPedidoLista = new ArrayList<>();

        for(Map.Entry<Product, Integer> mapaProducto : basketProducts.entrySet()){
            ProductEntity productEntityConfirmado = productoRepository.findById(mapaProducto.getKey().getId()).orElse(null);

            ProductToOrderEntity productoaPedido = new ProductToOrderEntity();

            productoaPedido.setProducto(productEntityConfirmado);
            productoaPedido.setPedido(orderEntity);
            productoaPedido.setCantidad(mapaProducto.getValue());

            productoAPedidoRepository.save(productoaPedido);
            productoaPedidoLista.add(productoaPedido);

            productEntityConfirmado.setPedidos(productoaPedidoLista);

            productoRepository.save(productEntityConfirmado);

            StockEntity stockEntityProducto;

            stockEntityProducto = inventarioRepository.findById(productEntityConfirmado.getInventario().getID()).orElse(null);
            if(stockEntityProducto != null && productEntityConfirmado.getInventario().getCantidad() > 0  ){
                stockEntityProducto.setCantidad(productEntityConfirmado.getInventario().getCantidad() - mapaProducto.getValue());
                inventarioRepository.save(stockEntityProducto);
            }
        }
        orderEntity.setProductos(productoaPedidoLista);

        pedidoRepository.save(orderEntity);
    }
}
