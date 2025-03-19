package com.example.prueba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
@NamedStoredProcedureQuery(
    name = "sp_insertAndListProducts",
    procedureName = "sp_insertAndListProducts",
    resultClasses = Producto.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_producto", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_registro", type = LocalDate.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_cursor", type = void.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_codigo_respuesta", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_mensaje_respuesta", type = String.class)
    }
)
public class Producto {

    @Id
    @Column(name = "id_producto")
    private Integer idProducto;
    private String nombre;
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    public String getFechaRegistroFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return fechaRegistro.format(formatter);
    }

}
