package com.example.prueba.service;

import com.example.prueba.model.Producto;
import com.example.prueba.model.ProductoResponse;
import com.example.prueba.repository.ProductoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProductoRepository productoRepository;

    @SuppressWarnings("unchecked")
    public ProductoResponse gestionarProductos(Integer id, String nombre, LocalDate fechaRegistro) {
        // Crea la llamada al procedimiento almacenado
        StoredProcedureQuery query = em
            .createNamedStoredProcedureQuery("sp_insertAndListProducts")
            .setParameter("p_id_producto", id)
            .setParameter("p_nombre", nombre)
            .setParameter("p_fecha_registro", fechaRegistro);

        // Ejecuta
        query.execute();

        Integer codigoRespuesta = (Integer) query.getOutputParameterValue("p_codigo_respuesta");
        String mensajeRespuesta = (String) query.getOutputParameterValue("p_mensaje_respuesta");

        List<Producto> productos;
        if (codigoRespuesta == 0) {
            productos = query.getResultList();
        } else {
            productos = Collections.emptyList();
        }

        return new ProductoResponse(productos, codigoRespuesta, mensajeRespuesta);
    }

}
