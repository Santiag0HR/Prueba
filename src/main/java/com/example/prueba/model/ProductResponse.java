package com.example.prueba.model;

import java.util.List;

public class ProductResponse {

    private int codigoRespueta;
    private String mensajeRespueta;
    private List<Product> productos;

    public int getCodigoRespueta() {
        return codigoRespueta;
    }

    public void setCodigoRespueta(int codigoRespueta) {
        this.codigoRespueta = codigoRespueta;
    }

    public String getMensajeRespueta() {
        return mensajeRespueta;
    }

    public void setMensajeRespueta(String mensajeRespueta) {
        this.mensajeRespueta = mensajeRespueta;
    }

    public List<Product> getProductos() {
        return productos;
    }

    public void setProductos(List<Product> productos) {
        this.productos = productos;
    }
}
