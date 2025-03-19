package com.example.prueba.model;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponse {

    private Integer codigoRespuesta;
    private String mensajeRespuesta;
    private List<ProductoDTO> productos;

    public ProductoResponse(List<Producto> productos, Integer codigoRespuesta, String mensajeRespuesta) {
        this.productos = productos.stream()
            .map(p -> new ProductoDTO(p.getIdProducto(), p.getNombre(), p.getFechaRegistroFormatted()))
            .collect(Collectors.toList());
        this.codigoRespuesta = codigoRespuesta;
        this.mensajeRespuesta = mensajeRespuesta;
    }

    @Getter
    @Setter
    public static class ProductoDTO {
        private Integer id;
        private String nombre;
        private String fechaRegistro;

        public ProductoDTO(Integer id, String nombre, String fechaRegistro) {
            this.id = id;
            this.nombre = nombre;
            this.fechaRegistro = fechaRegistro;
        }

    }
}
