package com.Backend.BacktotheGame.Controller;

import com.Backend.BacktotheGame.DTO.OrdenRequestDTO;
import com.Backend.BacktotheGame.Model.Producto;
import com.Backend.BacktotheGame.Repository.IproductoRepository;
import com.Backend.BacktotheGame.Service.IproductoService;
import com.Backend.BacktotheGame.Service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Backend.BacktotheGame.DTO.CompraRequestDTO;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*")
public class ProductoController {
    private final IproductoService iproductoService;

    public ProductoController(IproductoService iproductoService) {
        this.iproductoService = iproductoService;
    }

    @GetMapping
    public List<Producto> listaProducto(){
        return iproductoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Producto> obtenerporId(@PathVariable Long id){
        return iproductoService.obtenerporId(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarProducto(@RequestBody Producto producto){
        iproductoService.guardarProducto(producto);
        return ResponseEntity.ok("Guardado con exito");
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        iproductoService.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarProducto(@PathVariable Long id, @RequestBody Producto productoActual){
        iproductoService.editarProducto(id,productoActual);
        return  ResponseEntity.ok("Producto actualizado con exito");
    }

    @GetMapping("/contar/categoria/{idCategoria}")
    public ResponseEntity<Long> contarPorCategoria(@PathVariable Long idCategoria) {
        Long total = iproductoService.contarProductosPorCategoria(idCategoria);
        return ResponseEntity.ok(total);
    }

    @PostMapping("/comprar")
    public ResponseEntity<String> comprarProducto(@RequestBody CompraRequestDTO request) {

        iproductoService.comprarProducto(
                request.getIdProducto(),
                request.getCantidad()
        );

        return ResponseEntity.ok("Compra realizada con éxito");
    }


}
