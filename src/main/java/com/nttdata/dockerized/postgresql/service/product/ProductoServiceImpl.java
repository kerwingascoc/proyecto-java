package com.nttdata.dockerized.postgresql.service.product;

import com.nttdata.dockerized.postgresql.model.product.entity.Producto;
import com.nttdata.dockerized.postgresql.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
        if (producto == null) throw new IllegalArgumentException("El producto no puede ser nulo");
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado"));
    }


    @Override
    public Producto updateById(Long id, Producto producto) {
        if (id == null || producto == null)
            throw new IllegalArgumentException("El id y el producto no pueden ser nulos");

        Producto productoExistente = findById(id);
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        return productoRepository.save(productoExistente);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) throw new IllegalArgumentException("El id no puede ser nulo");
        Producto producto = findById(id);
        productoRepository.delete(producto);
    }
}
