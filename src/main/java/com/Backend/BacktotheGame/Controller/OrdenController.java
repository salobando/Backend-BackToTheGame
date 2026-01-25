package com.Backend.BacktotheGame.Controller;

import com.Backend.BacktotheGame.DTO.OrdenDTO;
import com.Backend.BacktotheGame.DTO.ProductoTopDTO;
import com.Backend.BacktotheGame.Model.Compra;
import com.Backend.BacktotheGame.Model.Orden;
import com.Backend.BacktotheGame.Model.Producto;
import com.Backend.BacktotheGame.Repository.IcompraRepository;
import com.Backend.BacktotheGame.Repository.IproductoRepository;
import com.Backend.BacktotheGame.Service.IordenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orden")
@CrossOrigin(origins = "*")
public class OrdenController {

    private final IordenService iordenService;
    private final IproductoRepository productoRepository;
    private final IcompraRepository compraRepository;

    public OrdenController(IordenService iordenService,  IproductoRepository productoRepository, IcompraRepository compraRepository) {
        this.iordenService = iordenService;
        this.productoRepository = productoRepository;
        this.compraRepository = compraRepository;
    }

    @GetMapping
    public List<Orden> listaOrden(){
        return iordenService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Orden> obtenerporId(@PathVariable Long id){
        return iordenService.obtenerporId(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarOrden(@RequestBody OrdenDTO dto) {

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Compra compra = compraRepository.findById(dto.getCompraId())
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        Orden orden = new Orden();
        orden.setCantidadP(dto.getCantidadP());
        orden.setPrecioUnitario(dto.getPrecioUnitario());
        orden.setProducto(producto);
        orden.setDetCompra(compra);

        iordenService.guardarOrden(orden);
        return ResponseEntity.ok("Guardado con exito");
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteOrden(@PathVariable Long id) {
        iordenService.eliminarOrden(id);
        return ResponseEntity.ok("Orden eliminada con éxito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarOrden(@PathVariable Long id, @RequestBody Orden ordenActual){
        iordenService.editarOrden(id, ordenActual);
        return  ResponseEntity.ok("Orden actualizada con exito");
    }

    @GetMapping("/productosTop")
    public List<ProductoTopDTO> productosMasVendidos() {
        return iordenService.obtenerProductosMasVendidos();
    }

}
