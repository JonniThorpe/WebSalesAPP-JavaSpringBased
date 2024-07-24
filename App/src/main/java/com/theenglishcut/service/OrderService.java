package com.theenglishcut.service;

import com.theenglishcut.dao.PedidoRepository;
import com.theenglishcut.dto.Order;
import com.theenglishcut.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends DTOService<Order, OrderEntity> {
    @Autowired
    protected PedidoRepository pedidoRepository;

    public List<Order> findOrderByUserId(Integer id) {
        return this.entidadesADTO(this.pedidoRepository.findPedidoByUser(id));
    }

    public List<Order> findAllOrders(){
        return this.entidadesADTO(this.pedidoRepository.findAll());
    }
}
