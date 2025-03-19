package com.example.prueba.service;

import com.example.prueba.model.Product;
import com.example.prueba.model.ProductResponse;
import com.example.prueba.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProductRepository productRepository;

    /*public ProductResponse registerProduct(Product product) {
        return productRepository.insertAndListProducts(
            product.getIdProducto(),
            product.getNombre(),
            product.getFecRegistro()
        );
    }*/

    @SuppressWarnings("unchecked")
    public List<Product> gestionarProductos(Integer id, String nombre) {
        // Crear la llamada al procedimiento almacenado
        StoredProcedureQuery query = em
            .createNamedStoredProcedureQuery("sp_insertAndListProducts")
            .setParameter("p_id_producto", id)
            .setParameter("p_nombre", nombre);

        // Ejecutar y obtener resultados
        return query.getResultList();
    }

}
