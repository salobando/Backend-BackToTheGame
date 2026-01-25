package com.Backend.BacktotheGame.DTO;

import java.math.BigDecimal;

public class ProductoTopDTO {

    private String nombreProducto;
    private Long totalVendido;

    public ProductoTopDTO() {
    }

    public ProductoTopDTO(String nombreProducto, Long totalVendido) {
        this.nombreProducto = nombreProducto;
        this.totalVendido = totalVendido;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }



    public Long getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Long totalVendido) {
        this.totalVendido = totalVendido;
    }
}
