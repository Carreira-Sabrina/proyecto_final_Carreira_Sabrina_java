package com.tiendatalentotech.crudproyectofinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tiendatalentotech.crudproyectofinal.dto.ProductoCrearRequestDto;
import com.tiendatalentotech.crudproyectofinal.exception.RecursoNoEncontradoException;
import com.tiendatalentotech.crudproyectofinal.model.Categoria;
import com.tiendatalentotech.crudproyectofinal.model.Producto;
import com.tiendatalentotech.crudproyectofinal.repository.CategoriaRepository;
import com.tiendatalentotech.crudproyectofinal.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;


    public ProductoService(ProductoRepository productoRespository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRespository;
        this.categoriaRepository = categoriaRepository;
    }

    //Crear un producto usando un DTO para comprobar si la categoría existe
    public Producto crearProducto(ProductoCrearRequestDto productoDTO){

        //findById de JpaRepository devuelve Optional
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(productoDTO.idCategoria());

        //Ahora hay que averiguar si en el Optional hay algo o no
        if(categoriaBuscada.isPresent()){
            //Hay una categoria en el Optional, hay que obtenerla primero para crear el producto
            Categoria categoriaEncontrada = categoriaBuscada.get();
            Producto nuevoProducto = new Producto();
            nuevoProducto.setCategoria(categoriaEncontrada);
            nuevoProducto.setDescripcion(productoDTO.descripcion());
            nuevoProducto.setNombre(productoDTO.nombre());
            nuevoProducto.setPrecio(productoDTO.precio());
            nuevoProducto.setUrlImagen(productoDTO.urlImagen());

            return productoRepository.save(nuevoProducto);
        } else{
            throw new RecursoNoEncontradoException("Categoria con el id " + productoDTO.idCategoria() + " no encontrada");
        }
    }

    //Obtener todos los productos
    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    //NO SE PUEDE DEVOLVER UN OPTIONAL CUANDO SE USAN EXCEPCIONES ! DEVUELVE EL PRODUCTO O LA EXCEPCION
    public Producto obtenerProductoPorId(Long id){
        return productoRepository.findById(id)
                .orElseThrow(()->new RecursoNoEncontradoException("Producto con id " + id + " no encontrado"));
    }

    
    

}
