package com.Backend.BacktotheGame.Service;

import com.Backend.BacktotheGame.Model.Categoria;
import com.Backend.BacktotheGame.Model.Producto;
import com.Backend.BacktotheGame.Repository.IcategoriaRepository;
import com.Backend.BacktotheGame.Repository.IproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IproductoService {

    private final IproductoRepository productoRepository;
    private final IcategoriaRepository categoriaRepository;

    @Autowired
    public ProductoService(IproductoRepository productoRepository,
                           IcategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerporId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public void guardarProducto(Producto producto) {

        // Validar que venga la categoría
        if (producto.getCategoria() == null || producto.getCategoria().getIdCategoria() == null) {
            throw new RuntimeException("La categoría es obligatoria");
        }

        // Buscar la categoría real en la BD
        Categoria categoria = categoriaRepository
                .findById(producto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Asociar la categoría al producto
        producto.setCategoria(categoria);

        if (producto.getImagen() == null || producto.getImagen().isBlank()) {
            throw new RuntimeException("La imagen (URL) es obligatoria");
        }

        // Guardar producto
        productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public void editarProducto(Long id, Producto productoActual) {

        Producto productoExistente = productoRepository.findById(id).orElse(null);

        if (productoExistente != null) {

            productoExistente.setNombre(productoActual.getNombre());
            productoExistente.setDescripcion(productoActual.getDescripcion());
            productoExistente.setPrecio(productoActual.getPrecio());
            productoExistente.setStock(productoActual.getStock());
            productoExistente.setImagen(productoActual.getImagen());

            // Actualizar categoría si viene
            if (productoActual.getCategoria() != null &&
                    productoActual.getCategoria().getIdCategoria() != null) {

                Categoria categoria = categoriaRepository
                        .findById(productoActual.getCategoria().getIdCategoria())
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

                productoExistente.setCategoria(categoria);
            }

            productoRepository.save(productoExistente);

        } else {
            throw new RuntimeException("Producto no encontrado por el id: " + id);
        }
    }
}
