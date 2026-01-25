package com.Backend.BacktotheGame.Service;

import com.Backend.BacktotheGame.DTO.ProductoTopDTO;
import com.Backend.BacktotheGame.Model.Compra;
import com.Backend.BacktotheGame.Model.Orden;
import com.Backend.BacktotheGame.Model.Producto;
import com.Backend.BacktotheGame.Repository.IcompraRepository;
import com.Backend.BacktotheGame.Repository.IordenRepository;
import com.Backend.BacktotheGame.Repository.IproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService implements IordenService{

    private final IordenRepository ordenRepository;
    private final IproductoRepository productoRepository;
    private final IcompraRepository compraRepository;

    @Autowired
    public OrdenService(IordenRepository ordenRepository, IproductoRepository productoRepository, IcompraRepository compraRepository) {

        this.ordenRepository = ordenRepository;
        this.productoRepository = productoRepository;
        this.compraRepository = compraRepository;
    }

    @Override
    public List<Orden> obtenerTodos() {
        return ordenRepository.findAll();
    }

    @Override
    public Optional<Orden> obtenerporId(Long id) {
        return ordenRepository.findById(id);
    }

    @Override
    public void guardarOrden(Orden orden) {
        // Validar que venga el producto
        if (orden.getProducto() == null || orden.getProducto().getIdProducto() == null) {
            throw new RuntimeException("El producto es obligatorio");
        }

        // Buscar la producto real en la BD
        Producto producto = productoRepository
                .findById(orden.getProducto().getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Validar que venga la compra
        if (orden.getDetCompra() == null || orden.getDetCompra().getId_compra() == null) {
            throw new RuntimeException("la compra es obligatoria");
        }

        // Buscar la compra real en la BD
        Compra compra = compraRepository
                .findById(orden.getDetCompra().getId_compra())
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        // Asociar el producto a la orden
        orden.setProducto(producto);
        // Asociar la compra a la orden
        orden.setDetCompra(compra);

        ordenRepository.save(orden);
    }

    @Override
    public void eliminarOrden(Long id) {
        ordenRepository.deleteById(id);
    }

    @Override
    public void editarOrden(Long id, Orden ordenActual) {
        Orden ordenExistente = ordenRepository.findById(id).orElse(null);

        if (ordenExistente != null){
            //Actualizar los campos existentes
            ordenExistente.setCantidadP(ordenActual.getCantidadP());
            ordenExistente.setPrecioUnitario(ordenActual.getPrecioUnitario());

            // Actualizar producto si viene
            if (ordenExistente.getProducto() != null && ordenExistente.getProducto().getIdProducto() != null) {

                Producto producto = productoRepository
                        .findById(ordenActual.getProducto().getIdProducto())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                ordenExistente.setProducto(producto);
            }

            // Actualizar compra si viene
            if (ordenExistente.getDetCompra() != null && ordenExistente.getDetCompra().getId_compra() != null) {

                Compra compra = compraRepository
                        .findById(ordenActual.getDetCompra().getId_compra())
                        .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

                ordenExistente.setDetCompra(compra);
            }

            // Guardar actualzacion
            ordenRepository.save(ordenExistente);
        } else {
            throw new RuntimeException("Orden no encontrada por el id: " + id);
        }
    }

    @Override
    public List<ProductoTopDTO> obtenerProductosMasVendidos() {
        return ordenRepository.obtenerProductosMasVendidos();
    }

}
