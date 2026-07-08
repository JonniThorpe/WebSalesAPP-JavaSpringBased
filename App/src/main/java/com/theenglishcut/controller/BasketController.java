package com.theenglishcut.controller;

import com.theenglishcut.dao.*;
import com.theenglishcut.dto.Order;
import com.theenglishcut.dto.Product;
import com.theenglishcut.dto.User;
import com.theenglishcut.entity.*;
import com.theenglishcut.service.*;
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
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    Map<Product,Integer> basketProducts= new Hashtable<>();
    private Order clientOrder = new Order();

    @GetMapping("/addProducto")
    public String addProducto (@RequestParam("id")int id, HttpSession sesion) {
        Product addProduct = productService.findByProductID(id);//Este va a ser el producto que vamos a meter
        boolean existe=false;
        for(Product product : basketProducts.keySet()){
            if(product.getId().equals(addProduct.getId())){
                existe=true;
                int unidades = basketProducts.get(product)+1;
                basketProducts.put(product,unidades);
            }
        }
        if(!existe){
            basketProducts.put(addProduct,1);
        }
        sesion.setAttribute("basketProducts",basketProducts);
        return "redirect:/listadoProductos?CategoryID=0";
    }

    @GetMapping("/confirmarPedidoCliente")
    public String confirmarPedidoCliente (Model model) {
        model.addAttribute("basketProducts", basketProducts);
        model.addAttribute("productQuantity", new productQuantity());

        model.addAttribute("quantityOptions", IntStream.rangeClosed(0, 64).boxed().collect(Collectors.toList()));
        return "/Carrito";
    }

    @PostMapping("/modifyBasketProduct")
    public String modifyBasketProduct(@ModelAttribute("productQuantity") productQuantity productQuantity) {
        // Lógica para manejar la modificación del producto en el carrito
        for(Product product : basketProducts.keySet()){
            if(product.getId().equals(productQuantity.getProductId())){
                basketProducts.put(product,productQuantity.getQuantity());
            }
        }
        return "redirect:/Basket/confirmarPedidoCliente"; // Redirigir después de manejar la solicitud
    }
    @GetMapping("/deleteProduct")
    public String delete_Product(@RequestParam("idProduct")Integer idProduct) {
        for(Product product : basketProducts.keySet()){
            if(product.getId().equals(idProduct)){
                basketProducts.remove(product);
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
        User client = userService.findByUsername(user.getNombre());

        orderService.save(client, clientOrder, basketProducts);

        //Limpiamos el carrito para nuevos productos y nuevo pedido
        basketProducts.clear();

        clientOrder = new Order();

        return "redirect:/listadoProductos?CategoryID=0";
    }

}
