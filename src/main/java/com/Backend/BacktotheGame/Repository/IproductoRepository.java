package com.Backend.BacktotheGame.Repository;

import com.Backend.BacktotheGame.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IproductoRepository extends JpaRepository<Producto, Long> {

    //contar cantidad de productos por categoria
    @Query("SELECT COUNT(p) FROM Producto p WHERE p.categoria.idCategoria = :idCategoria")
    Long contarPorCategoria(@Param("idCategoria") Long idCategoria);
}
