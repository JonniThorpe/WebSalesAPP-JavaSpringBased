package com.theenglishcut.service;

import com.theenglishcut.dao.*;
import com.theenglishcut.dto.DTO;
import com.theenglishcut.dto.Product;
import com.theenglishcut.dto.Stock;
import com.theenglishcut.entity.ProductEntity;
import com.theenglishcut.entity.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService extends DTOService<Product, ProductEntity> {
    @Autowired
    protected ProductoRepository productRepository;
    @Autowired
    protected CategoriaRepository categoryRepository;
    @Autowired
    protected InventarioRepository stockRepository;
    @Autowired
    protected ProductoACategoriaRepository productToCategoryRepository;
    @Autowired
    protected ProductoAPedidoRepository orderToProductRepository;
    @Autowired
    private InventarioRepository inventarioRepository;
    //List of prodructs

    public List<Product> listedProducts (){
        List<ProductEntity> list = productRepository.findAll();
        return this.entidadesADTO(list);
    }

    public List<Product> listedProductsByCategory(Integer categoryID){
        List<ProductEntity> list = productRepository.findByCategoryID(categoryID);
        return this.entidadesADTO(list);
    }

    public List<Product> listedProductsByCategories(List<Integer> categoryIDs){
        List<Product> products = new ArrayList<>();
        for(Integer categoryID : categoryIDs){
            products.addAll(this.listedProductsByCategory(categoryID));
        }
        return products;
    }

    public void deleteProduct(Integer id){
        this.productRepository.deleteById(id);
    }

    public Product findByProductID(Integer id){
        ProductEntity product = productRepository.findById(id).orElse(null);
        if(product != null){
            return  product.toDTO();
        }else{
            return null;
        }
    }

    public void saveProduct(Product product, Integer quantity){
        ProductEntity productEntity = this.productRepository.findById(product.getId()).orElse(new ProductEntity());
        productEntity.setNombre(product.getName());
        productEntity.setDescripcion(product.getDescription());
        productEntity.setImagen(product.getImage());
        productEntity.setPrecio(product.getPrice());

        //Crea el inventario si el producto no existe
        if(productEntity.getID() == null){
            saveStock(productEntity, new StockEntity(), quantity);

        }else{//actualiza el inventario ya existente si el producto existe
            StockEntity stock = this.stockRepository.findById(product.getStock().getId()).orElse(null);
            saveStock(productEntity, stock, quantity);
        }
        productEntity.setCategorias(this.productToCategoryRepository.findProductToCategoryByProductID(product.getId()));
        productEntity.setPedidos(this.orderToProductRepository.findProductToOrderByProductID(product.getId()));

        this.productRepository.save(productEntity);
    }

    private void saveStock(ProductEntity productEntity, StockEntity stock, Integer quantity){
        stock.setCantidad(quantity);
        inventarioRepository.save(stock);
        productEntity.setInventario(stock);
    }
}
