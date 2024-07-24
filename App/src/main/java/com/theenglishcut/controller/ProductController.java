package com.theenglishcut.controller;

import com.theenglishcut.dao.*;
import com.theenglishcut.dto.Product;
import com.theenglishcut.entity.*;
import com.theenglishcut.service.CategoryService;
import com.theenglishcut.service.ProductService;
import com.theenglishcut.service.ProductToCategoryService;
import com.theenglishcut.service.StockService;
import com.theenglishcut.ui.producto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductToCategoryService productToCategoryService;

    private Integer categoriaGlobal;

    /**
     * Devuelve el listado de productos de la web
     * @return listadoProductos.jsp
     */
    @GetMapping("/listadoProductos")
    public String verProductos (Model model, @RequestParam("CategoryID") Integer categoryID) {
        List<Product> productList;
        if(categoryID == 0){
            categoriaGlobal = 0;
            productList = productService.listedProducts();
        }else {
            categoriaGlobal = categoryID;
            productList = productService.listedProductsByCategory(categoryID);
        }

        if(productList.isEmpty()){
            model.addAttribute("mensaje", "No hay productos");
        }

        model.addAttribute("products", productList);
        return "/listadoProductos";
    }
    @GetMapping("/Navbar")
    public String verCategorias (HttpSession sesion) {

        sesion.setAttribute("categoryListView", categoryService.findAll());
        return "/Navbar";
    }

    @GetMapping("/Detail")
    public String product_Detail(@RequestParam("id")Integer id, Model modelo){
        Product product = productService.findByProductID(id);

        modelo.addAttribute("product", product);
        return "/ProductDetail";
    }

    @GetMapping("/CrearProducto")
    public String crearProducto (Model modelo) {
        modelo.addAttribute("product",  new Product());
        modelo.addAttribute("categories",categoryService.findAll());
        modelo.addAttribute("quantity",0);

        return "/CrearProducto";
    }

    @GetMapping("/modificarProducto")
    public String modificarProducto (Model modelo, @RequestParam("id") Integer id) {
        Product modifiedProduct = productService.findByProductID(id);

        modelo.addAttribute("categories",categoryService.findAll());
        modelo.addAttribute("product",  modifiedProduct);
        modelo.addAttribute("quantity",modifiedProduct.getStock().getQuantity());
        return "/CrearProducto";
    }

    @GetMapping("/eliminarProducto")
    public String eliminarProducto (Model model, @RequestParam("id")int id) {
        Product product = this.productService.findByProductID(id);
        if(product == null){
            System.out.println("ningun producto eliminado, no deberia ser null el producto");
            return "redirect:/listadoProductos?CategoryID="+categoriaGlobal;
        }
        this.productToCategoryService.deleteByProductId(id);
        this.productService.deleteProduct(id);
        this.stockService.deleteByStockID(product.getStock().getId());
        return "redirect:/listadoProductos?CategoryID="+categoriaGlobal;
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto (Model modelo, @ModelAttribute("product")Product product, @RequestParam("quantity")Integer quantity) {

        this.productService.saveProduct(product, quantity);


        return "redirect:/listadoProductos?CategoryID="+categoriaGlobal;
    }

}
