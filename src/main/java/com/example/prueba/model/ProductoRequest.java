package com.example.prueba.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequest {

    private Integer id;
    private String nombre;
    private String fechaRegistro;

    public LocalDate getFechaRegistroAsLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fechaRegistro, formatter);
    }

}
