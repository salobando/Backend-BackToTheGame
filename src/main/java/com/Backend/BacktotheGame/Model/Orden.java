package com.Backend.BacktotheGame.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_orden;
    @Column(nullable = false)
    private long id_compra;
    @Column(nullable = false)
    private long id_producto;

    public Orden() {
    }

    public Orden(long id_orden, long id_compra, long id_producto) {
        this.id_orden = id_orden;
        this.id_compra = id_compra;
        this.id_producto = id_producto;
    }

    public long getId_orden() {
        return id_orden;
    }

    public void setId_orden(long id_orden) {
        this.id_orden = id_orden;
    }

    public long getId_compra() {
        return id_compra;
    }

    public void setId_compra(long id_compra) {
        this.id_compra = id_compra;
    }

    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }
}
