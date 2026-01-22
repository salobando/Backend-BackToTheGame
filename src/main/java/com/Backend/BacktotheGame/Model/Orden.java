package com.Backend.BacktotheGame.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_orden;

    @Column(nullable = false)
    private int cantidadP;

    @Column(nullable = false)
    private BigDecimal precioUnitario;

    // MUCHOS items de orden pertenecen a UN producto
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonBackReference("orden-producto")
    private Producto producto;

    // MUCHAS ordenes pertenecen a UNA compra
    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    @JsonBackReference("orden-compra")
    private Compra detCompra;

    public Orden() {
    }

    public Orden(Long id_orden, int cantidadP, BigDecimal precioUnitario) {
        this.id_orden = id_orden;
        this.cantidadP = cantidadP;
        this.precioUnitario = precioUnitario;
    }

    public Long getId_orden() {
        return id_orden;
    }

    public void setId_orden(Long id_orden) {
        this.id_orden = id_orden;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Compra getDetCompra() {
        return detCompra;
    }

    public void setDetCompra(Compra detCompra) {
        this.detCompra = detCompra;
    }
}
