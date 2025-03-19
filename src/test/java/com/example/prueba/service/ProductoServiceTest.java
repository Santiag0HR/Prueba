package com.example.prueba.service;

import com.example.prueba.model.Producto;
import com.example.prueba.model.ProductoResponse;
import com.example.prueba.repository.ProductoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @InjectMocks
    private ProductoService productoService;

    private LocalDate fechaRegistro;

    @BeforeEach
    void setUp() {
        fechaRegistro = LocalDate.of(2025, 3, 19);
        when(entityManager.createNamedStoredProcedureQuery(anyString())).thenReturn(storedProcedureQuery);
    }

    @Test
    void testGestionarProductosOk() {
        Integer idProducto = 1;
        String nombre = "ProductoNuevo";

        // Crea una instancia de la entidad Producto
        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        producto.setNombre(nombre);
        producto.setFechaRegistro(fechaRegistro);

        List<Producto> productosEsperados = List.of(producto);

        // Configura comportamiento del mock
        when(storedProcedureQuery.setParameter("p_id_producto", idProducto)).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.setParameter("p_nombre", nombre)).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.setParameter("p_fecha_registro", fechaRegistro)).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.execute()).thenReturn(true);
        when(storedProcedureQuery.getOutputParameterValue("p_codigo_respuesta")).thenReturn(0);
        when(storedProcedureQuery.getOutputParameterValue("p_mensaje_respuesta")).thenReturn("Ejecución con éxito");
        when(storedProcedureQuery.getResultList()).thenReturn(productosEsperados);

        ProductoResponse response = productoService.gestionarProductos(idProducto, nombre, fechaRegistro);

        assertNotNull(response);
        assertEquals(0, response.getCodigoRespuesta());
        assertEquals("Ejecución con éxito", response.getMensajeRespuesta());
        assertEquals(1, response.getProductos().size());
        assertEquals(idProducto, response.getProductos().get(0).getId());
        assertEquals(nombre, response.getProductos().get(0).getNombre());
        assertEquals("20250319", response.getProductos().get(0).getFechaRegistro());

        // Verificar interacciones
        verify(storedProcedureQuery, times(1)).execute();
        verify(storedProcedureQuery, times(1)).getResultList();
    }

    @Test
    void testGestionarProductosError() {

        Integer idProducto = 1;
        String nombre = "ProductoDuplicado";
        String mensajeError = "Error: -1 - ORA-00001: unique constraint violated";

        // Configurar comportamiento del mock
        when(storedProcedureQuery.setParameter("p_id_producto", idProducto)).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.setParameter("p_nombre", nombre)).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.setParameter("p_fecha_registro", fechaRegistro)).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.execute()).thenReturn(true);
        when(storedProcedureQuery.getOutputParameterValue("p_codigo_respuesta")).thenReturn(1);
        when(storedProcedureQuery.getOutputParameterValue("p_mensaje_respuesta")).thenReturn(mensajeError);

        ProductoResponse response = productoService.gestionarProductos(idProducto, nombre, fechaRegistro);

        assertNotNull(response);
        assertEquals(1, response.getCodigoRespuesta());
        assertEquals(mensajeError, response.getMensajeRespuesta());
        assertTrue(response.getProductos().isEmpty());

        // Verificar interacciones
        verify(storedProcedureQuery, times(1)).execute();
        verify(storedProcedureQuery, never()).getResultList(); // No se llama en caso de error
    }
}