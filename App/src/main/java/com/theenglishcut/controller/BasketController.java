package com.theenglishcut.controller;

import com.theenglishcut.dao.*;
import com.theenglishcut.entity.*;
import com.theenglishcut.ui.productQuantity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("Basket")
public class BasketController {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoAPedidoRepository PedidoaProductoRepository;

    Map<ProductEntity,Integer> productosCarrito= new Hashtable<>();
    private OrderEntity orderEntityCliente = new OrderEntity();

    @GetMapping("/addProducto")
    public String addProducto (@RequestParam("id")int id) {
        ProductEntity productEntity_meter = productoRepository.findById(id).get();//Este va a ser el producto que vamos a meter
        boolean existe=false;
        for(ProductEntity productEntity : productosCarrito.keySet()){
            if(productEntity.getID().equals(productEntity_meter.getID())){
                existe=true;
                int unidades = productosCarrito.get(productEntity)+1;
                productosCarrito.put(productEntity,unidades);
            }
        }
        if(!existe){
            productosCarrito.put(productEntity_meter,1);
        }
        return "redirect:/listadoProductos?CategoryID=0";
    }

    @GetMapping("/confirmarPedidoCliente")
    public String confirmarPedidoCliente (Model model) {
        model.addAttribute("productosCarrito", productosCarrito);
        model.addAttribute("productQuantity", new productQuantity());

        model.addAttribute("quantityOptions", IntStream.rangeClosed(0, 64).boxed().collect(Collectors.toList()));
        return "/Carrito";
    }

    @PostMapping("/modifyBasketProduct")
    public String modifyBasketProduct(@ModelAttribute("productQuantity") productQuantity productQuantity) {
        // Lógica para manejar la modificación del producto en el carrito
        for(ProductEntity productEntity : productosCarrito.keySet()){
            if(productEntity.getID().equals(productQuantity.getProductId())){
                productosCarrito.put(productEntity,productQuantity.getQuantity());
            }
        }
        return "redirect:/Basket/confirmarPedidoCliente"; // Redirigir después de manejar la solicitud
    }
    @GetMapping("/deleteProduct")
    public String delete_Product(@RequestParam("idProduct")Integer idProduct) {
        for(ProductEntity productEntity : productosCarrito.keySet()){
            if(productEntity.getID().equals(idProduct)){
                productosCarrito.remove(productEntity);
            }
        }
        return "redirect:/Basket/confirmarPedidoCliente";
    }

    @PostMapping("/confirmarPedido")
    public String confirmar_Producto_a_Pedido (HttpSession sesion) {

        UserEntity user = (UserEntity) sesion.getAttribute("user");
        if (user == null) {
            // Manejar el caso en que el usuario no esté autenticado
            return "redirect:/login"; // Redirigir a la página de inicio de sesión
        }
        UserEntity clientePedido = usuarioRepository.findByNombreUser(user.getNombre());


        List<ProductToOrderEntity> productoaPedidoLista = new ArrayList<>();


        //Creamos el pedido
        orderEntityCliente.setUsuario(clientePedido);
        orderEntityCliente.setFechaCreacion(new Date());
        orderEntityCliente.setEntregaCompletada(false);
        pedidoRepository.save(orderEntityCliente);


        for(Map.Entry<ProductEntity, Integer> mapaProducto : productosCarrito.entrySet()){
            ProductEntity productEntityConfirmado = mapaProducto.getKey();

            ProductToOrderEntity productoaPedido = new ProductToOrderEntity();

            productoaPedido.setProducto(productEntityConfirmado);
            productoaPedido.setPedido(orderEntityCliente);
            productoaPedido.setCantidad(mapaProducto.getValue());

            PedidoaProductoRepository.save(productoaPedido);
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
        orderEntityCliente.setProductos(productoaPedidoLista);

        pedidoRepository.save(orderEntityCliente);

        //Limpiamos el carrito para nuevos productos y nuevo pedido
        productosCarrito.clear();

        orderEntityCliente = new OrderEntity();

        return "redirect:/listadoProductos?CategoryID=0";
    }

}
