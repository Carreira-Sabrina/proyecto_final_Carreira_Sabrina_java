package com.tiendatalentotech.crudproyectofinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendatalentotech.crudproyectofinal.exception.RecursoNoEncontradoException;
import com.tiendatalentotech.crudproyectofinal.model.Producto;
import com.tiendatalentotech.crudproyectofinal.repository.ProductoRespository;

@Service
public class ProductoService {

    @Autowired
    private final ProductoRespository productoRespository;

    public ProductoService(ProductoRespository productoRespository) {
        this.productoRespository = productoRespository;
    }

    //Crear un producto
    public Producto crearProducto(Producto producto){
        return productoRespository.save(producto);
    }

    //Obtener todos los productos
    public List<Producto> listarProductos(){
        return productoRespository.findAll();
    }

    //NO SE PUEDE DEVOLVER UN OPTIONAL CUANDO SE USAN EXCEPCIONES ! DEVUELVE EL PRODUCTO O LA EXCEPCION
    public Producto obtenerProductoPorId(Long id){
        return productoRespository.findById(id)
                .orElseThrow(()->new RecursoNoEncontradoException("Producto con id " + id + " no encontrado"));
    }

    
    

}
