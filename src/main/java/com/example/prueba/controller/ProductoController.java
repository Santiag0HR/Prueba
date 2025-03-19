package com.example.prueba.controller;

import com.example.prueba.model.ProductoRequest;
import com.example.prueba.model.ProductoResponse;
import com.example.prueba.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponse> crearYListarProductos(@RequestBody ProductoRequest request) {
        ProductoResponse response = productoService.gestionarProductos(
            request.getId(),
            request.getNombre(),
            request.getFechaRegistroAsLocalDate());
        return ResponseEntity.ok(response);
    }

}
