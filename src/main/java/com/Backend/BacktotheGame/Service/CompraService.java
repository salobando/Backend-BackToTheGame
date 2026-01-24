package com.Backend.BacktotheGame.Service;

import com.Backend.BacktotheGame.Model.Categoria;
import com.Backend.BacktotheGame.Model.Cliente;
import com.Backend.BacktotheGame.Model.Compra;
import com.Backend.BacktotheGame.Repository.IclienteRepository;
import com.Backend.BacktotheGame.Repository.IcompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService implements IcompraService{

    private final IcompraRepository compraRepository;
    private final IclienteRepository clienteRepository;

    @Autowired
    public CompraService(IcompraRepository compraRepository, IclienteRepository clienteRepository) {

        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Compra> obtenerTodos() {
        return compraRepository.findAll();
    }

    @Override
    public Optional<Compra> obtenerporId(Long id) {
        return compraRepository.findById(id);
    }

    @Override
    public void guardarCompra(Compra compra) {
        // Validar que venga el cliente
        if (compra.getComprador() == null || compra.getComprador().getId_cliente() == null) {
            throw new RuntimeException("El cliente es obligatorio");
        }

        // Buscar la cliente real en la BD
        Cliente cliente = clienteRepository
                .findById(compra.getComprador().getId_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrada"));

        // Asociar la cliente a lña compra
        compra.setComprador(cliente);
        compraRepository.save(compra);
    }

    @Override
    public void eliminarCompra(Long id) {
        compraRepository.deleteById(id);
    }

    @Override
    public void editarCompra(Long id, Compra compraActual) {

        Compra compraExistente = compraRepository.findById(id).orElse(null);

        if (compraExistente != null){
            //Actualizar los campos existentes
            compraExistente.setFechaCompra(compraActual.getFechaCompra());
            compraExistente.setTotal(compraActual.getTotal());
            compraExistente.setEstado(compraActual.getEstado());
            compraExistente.setComprador(compraActual.getComprador());

            // Actualizar cliente si viene
            if (compraExistente.getComprador() != null &&
                    compraExistente.getComprador().getId_cliente() != null) {

                Cliente cliente = clienteRepository
                        .findById(compraActual.getComprador().getId_cliente())
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

                compraExistente.setComprador(cliente);
            }
            // Guardar actualzacion
            compraRepository.save(compraExistente);

        } else {
            throw new RuntimeException("Compra no encontrada por el id: " + id);
        }
    }
}
