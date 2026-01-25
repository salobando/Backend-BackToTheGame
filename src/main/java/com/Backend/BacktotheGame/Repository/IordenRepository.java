package com.Backend.BacktotheGame.Repository;

import com.Backend.BacktotheGame.DTO.ProductoTopDTO;
import com.Backend.BacktotheGame.Model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IordenRepository extends JpaRepository<Orden, Long> {

    @Query("""
        SELECT new com.Backend.BacktotheGame.DTO.ProductoTopDTO(
            p.nombre,
            SUM(o.cantidadP)
        )
        FROM Orden o
        JOIN o.producto p
        GROUP BY p.nombre
        ORDER BY SUM(o.cantidadP) DESC
    """)
    List<ProductoTopDTO> obtenerProductosMasVendidos();
}
