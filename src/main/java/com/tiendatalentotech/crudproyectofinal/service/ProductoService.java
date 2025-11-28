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

    //BORRAR UN PRODUCTO POR ID
    public void eliminarProductoPorId(Long id){
        //No se puede borrar algo que no existe
        if(!productoRepository.existsById(id)){
            throw new RecursoNoEncontradoException("No existe un producto con el id " + id);
        }

        productoRepository.deleteById(id);
    }


    //ACTUALIZAR UN PRODUCTO
    //MUCHO CUIDADO CON ESTO, LA IDEA ES QUE DESDE EL FRONT PODRÍAN NO VENIR TODOS LOS DATOS DE UN PRODUCTO
    public Producto actualizarProducto(Long id, ProductoCrearRequestDto productoDTO){
        //Antes de intentar actualizar algo hay que verificar que exista
        if(!productoRepository.existsById(id)){
            throw new RecursoNoEncontradoException("No existe un producto con el id " + id);
        }

        //Hay que tener cuidado que la nueva categoria sea valida
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(productoDTO.idCategoria());

        //Ahora hay que averiguar si en el Optional hay algo o no
        if(categoriaBuscada.isPresent()){
            //Hay una categoria en el Optional, hay que obtenerla primero para crear el producto
            Categoria categoriaEncontrada = categoriaBuscada.get();
            //SIENTO QUE FALTAN MEDIO MILLON DE CONTROLES PARA QUE NO VENGA BASURA DEL FRONT ! 🦜🦜🦜🦜
            Producto productoModificado = new Producto();
            productoModificado.setCategoria(categoriaEncontrada);
            productoModificado.setId(id);
            productoModificado.setDescripcion(productoDTO.descripcion());
            productoModificado.setNombre(productoDTO.nombre());
            productoModificado.setPrecio(productoDTO.precio());
            productoModificado.setUrlImagen(productoDTO.urlImagen());

            return productoRepository.save(productoModificado);
        }else{
            throw new RecursoNoEncontradoException("Categoria con el id " + productoDTO.idCategoria() + " no encontrada");
        }
    }

}
