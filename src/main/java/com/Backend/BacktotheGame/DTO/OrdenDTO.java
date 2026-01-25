package com.Backend.BacktotheGame.DTO;

import java.math.BigDecimal;

public class OrdenDTO {
    private int cantidadP;
    private BigDecimal precioUnitario;
    private Long productoId;
    private Long compraId;

    public OrdenDTO() {
    }

    public OrdenDTO(int cantidadP, BigDecimal precioUnitario, Long productoId, Long compraId) {
        this.cantidadP = cantidadP;
        this.precioUnitario = precioUnitario;
        this.productoId = productoId;
        this.compraId = compraId;
    }

    public int getCantidadP() {
        return cantidadP;
    }

    public void setCantidadP(int cantidadP) {
        this.cantidadP = cantidadP;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }
}
