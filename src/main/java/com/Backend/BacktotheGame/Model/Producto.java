package com.Backend.BacktotheGame.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_producto;
    @Column(nullable = false, length = 250)
    private String nombre;
    @Column(nullable = false, length = 250)
    private String descripcion;
    @Column(nullable = false)
    private long precio;
    @Column(nullable = false)
    private int stock;
    @Column(nullable = false)
    private int id_categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    Categoria categoria;


    public Producto() {

    }

    public Producto(long id_producto, String nombre, String descripcion, long precio, int stock, int id_categoria) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.id_categoria = id_categoria;
    }

    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}
