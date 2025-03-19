package com.example.prueba.repository;

import com.example.prueba.model.Product;
import com.example.prueba.model.ProductResponse;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    /*@Procedure(procedureName = "sp_insertAndListProducts")
    ProductResponse insertAndListProducts(int idProducto, String nombre, String fecRegistro);*/

}
