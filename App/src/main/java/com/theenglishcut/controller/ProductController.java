package com.theenglishcut.controller;

import com.theenglishcut.dao.*;
import com.theenglishcut.entity.*;
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
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoACategoriaRepository productoACategoriaRepository;

    private Integer categoriaGlobal;

    /**
     * Devuelve el listado de productos de la web
     * @return listadoProductos.jsp
     */
    @GetMapping("/listadoProductos")
    public String verProductos (Model model, @RequestParam("CategoryID") Integer categoryID) {
        List<ProductEntity> productEntityList;
        if(categoryID == 0){
            categoriaGlobal = 0;
            productEntityList = productoRepository.findAll();
        }else {
            categoriaGlobal = categoryID;
            productEntityList = productoRepository.findByCategoryID(categoryID);
        }

        if(productEntityList.isEmpty()){
            model.addAttribute("mensaje", "No hay productos");
        }

        model.addAttribute("products", productEntityList);
        return "/listadoProductos";
    }
    @GetMapping("/Navbar")
    public String verCategorias (HttpSession sesion) {

        List<CategoryEntity> categoryList = categoriaRepository.findAll();
        sesion.setAttribute("categoryListView", categoryList);
        return "/Navbar";
    }

    @GetMapping("/Detail")
    public String product_Detail(@RequestParam("id")Integer id, Model modelo){
        ProductEntity product = productoRepository.findById(id).get();

        modelo.addAttribute("product", product);
        return "/ProductDetail";
    }

    @GetMapping("/CrearProducto")
    public String crearProducto (Model modelo) {
        modelo.addAttribute("producto",  new producto());
        modelo.addAttribute("categorias",categoriaRepository.findAll());
        return "/CrearProducto";
    }

    @GetMapping("/modificarProducto")
    public String modificarProducto (Model modelo, @RequestParam("id") Integer id) {
        producto productoModificado = new producto();
        ProductEntity productEntity = productoRepository.findById(id).get();

        productoModificado.setIdProducto(id);
        productoModificado.setNombre(productEntity.getNombre());
        productoModificado.setDescripcion(productEntity.getDescripcion());
        productoModificado.setPrecio(productEntity.getPrecio());
        productoModificado.setImagen(productEntity.getImagen());
        productoModificado.setCantidad(productEntity.getInventario().getCantidad());
        productoModificado.setCategorias(productEntity.getCategorias().stream()
                .map(ProductToCategoryEntity::getCategoria)
                .map(CategoryEntity::getID)
                .collect(Collectors.toList()));

        modelo.addAttribute("categorias",categoriaRepository.findAll());
        modelo.addAttribute("producto",  productoModificado);
        return "/CrearProducto";
    }

    @GetMapping("/eliminarProducto")
    public String eliminarProducto (Model model, @RequestParam("id")int id) {
        ProductEntity productEntity = productoRepository.findById(id).orElse(null);
        if(productEntity == null){
            System.out.println("ningun producto eliminado, no deberia ser null el producto");
            return "redirect:/listadoProductos?CategoryID="+categoriaGlobal;
        }
        productoACategoriaRepository.deleteByProductID(id);
        productoRepository.deleteById(id);
        inventarioRepository.deleteById(productEntity.getInventario().getID());
        return "redirect:/listadoProductos?CategoryID="+categoriaGlobal;
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto (Model modelo, @ModelAttribute("producto")producto producto) {
        ProductEntity productEntityNuevo = new ProductEntity();
        StockEntity stockEntity = new StockEntity();

        if(producto.getIdProducto() == null) {
            stockEntity.setCantidad(producto.getCantidad());
            inventarioRepository.save(stockEntity);
            productEntityNuevo.setInventario(stockEntity);

            GuardarObjetoProducto(producto, productEntityNuevo);
        }else{
            productEntityNuevo = productoRepository.findById(producto.getIdProducto()).orElse(null);

            stockEntity = productEntityNuevo.getInventario();
            stockEntity.setCantidad(producto.getCantidad());
            inventarioRepository.save(stockEntity);

            productoACategoriaRepository.deleteByProductID(producto.getIdProducto());
            productEntityNuevo.getCategorias().clear();

            GuardarObjetoProducto(producto, productEntityNuevo);

        }
        return "redirect:/listadoProductos?CategoryID="+categoriaGlobal;
    }

    private void GuardarObjetoProducto(@ModelAttribute("producto") producto producto, ProductEntity productEntityNuevo) {

        productEntityNuevo.setNombre(producto.getNombre());
        productEntityNuevo.setDescripcion(producto.getDescripcion());
        productEntityNuevo.setImagen(producto.getImagen());
        productEntityNuevo.setPrecio(producto.getPrecio());
        productoRepository.save(productEntityNuevo);

        List<ProductToCategoryEntity> categoriasProducto = new ArrayList<>();

        for (CategoryEntity c : categoriaRepository.findAllById(producto.getCategorias())) {
            ProductToCategoryEntity pc = new ProductToCategoryEntity();

            pc.setProducto(productEntityNuevo);
            pc.setCategoria(c);

            categoriasProducto.add(pc);

            productoACategoriaRepository.save(pc);
        }

        productEntityNuevo.setCategorias(categoriasProducto);
    }

}
