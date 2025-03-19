package com.example.prueba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "productos")
@NamedStoredProcedureQuery(
    name = "sp_insertAndListProducts",
    procedureName = "sp_insertAndListProducts",
    resultClasses = Product.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_producto", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_cursor", type = void.class)
    }
)
public class Product {

    @Id
    @Column(name = "id_producto")
    private Integer idProducto;
    private String nombre;
    @Column(name = "fec_registro")
    private String fecRegistro;

}
