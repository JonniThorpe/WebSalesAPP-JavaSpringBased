package com.theenglishcut.service;

import com.theenglishcut.dao.*;
import com.theenglishcut.dto.Product;
import com.theenglishcut.entity.CategoryEntity;
import com.theenglishcut.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends DTOService<Product, ProductEntity>{
    @Autowired
    protected ProductoRepository productRepository;
    @Autowired
    protected CategoriaRepository categoryRepository;
    @Autowired
    protected InventarioRepository stockRepository;
    @Autowired
    protected ProductoACategoriaRepository productToCategoryRepository;
    @Autowired
    protected PedidoAProductoRepository orderToProductRepository;
    //List of prodructs

    public List<Product> listedProducts (){
        List<ProductEntity> list = productRepository.findAll();
        return this.entidadesADTO(list);
    }

    public List<Product> listedProductsByCategory(Integer categoryID){
        List<ProductEntity> list = productRepository.findByCategoryID(categoryID);
        return this.entidadesADTO(list);
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

    public void saveProduct(Product product){
        ProductEntity productEntity = this.productRepository.findById(product.getId()).orElse(new ProductEntity());
        productEntity.setNombre(product.getName());
        productEntity.setDescripcion(product.getDescription());
        productEntity.setImagen(product.getImage());
        productEntity.setPrecio(product.getPrice());
        productEntity.setInventario(this.stockRepository.findById(product.getStock().getId()).orElse(null));
        productEntity.setCategorias(this.productToCategoryRepository.findProductToCategoryByProductID(product.getId()));
        productEntity.setPedidos(this.orderToProductRepository.findProductToOrderByProductID(product.getId()));

        this.productRepository.save(productEntity);
    }
}
