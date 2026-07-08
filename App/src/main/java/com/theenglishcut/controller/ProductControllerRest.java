package com.theenglishcut.controller;

import com.theenglishcut.dto.Category;
import com.theenglishcut.dto.Product;
import com.theenglishcut.service.CategoryService;
import com.theenglishcut.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Thin JSON pass-through over the existing services, for the React frontend in /web.
 * No business logic lives here; it only reshapes the same DTOs already used by the JSP views.
 */
@RestController
@RequestMapping("/rest")
public class ProductControllerRest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public List<Product> listProducts(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
        if (categoryId == null || categoryId == 0) {
            return productService.listedProducts();
        }
        return productService.listedProductsByCategory(categoryId);
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productService.findByProductID(id);
    }

    @GetMapping("/categories")
    public List<Category> listCategories() {
        return categoryService.findAll();
    }
}
