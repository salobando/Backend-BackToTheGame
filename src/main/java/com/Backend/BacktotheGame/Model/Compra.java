package com.Backend.BacktotheGame.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_compra;
    @Column(nullable = false)
    private int cantidadP;
    @Column(nullable = false)
    private long id_cliente;

    public Compra() {
    }

    public Compra(long id_compra, int cantidadP, long id_cliente) {
        this.id_compra = id_compra;
        this.cantidadP = cantidadP;
        this.id_cliente = id_cliente;
    }

    public long getId_compra() {
        return id_compra;
    }

    public void setId_compra(long id_compra) {
        this.id_compra = id_compra;
    }

    public int getCantidadP() {
        return cantidadP;
    }

    public void setCantidadP(int cantidadP) {
        this.cantidadP = cantidadP;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }
}
