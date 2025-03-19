package com.example.prueba.controller;

import com.example.prueba.model.Product;
import com.example.prueba.model.ProductResponse;
import com.example.prueba.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*@PostMapping
    public ProductResponse registerProduct(@RequestBody Product product) {
        return productService.registerProduct(product);
    }*/

    @PostMapping
    public List<Product> crearYListarProductos(
        @RequestParam Integer id,
        @RequestParam String nombre) {
        return productService.gestionarProductos(id, nombre);
    }

}
