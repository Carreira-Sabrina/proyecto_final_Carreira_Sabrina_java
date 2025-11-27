package com.tiendatalentotech.crudproyectofinal.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.tiendatalentotech.crudproyectofinal.exception.RecursoNoEncontradoException;
import com.tiendatalentotech.crudproyectofinal.model.Categoria;
import com.tiendatalentotech.crudproyectofinal.repository.CategoriaRepository;

@Service
public class CategoriaService {
    

    private final CategoriaRepository categoriaRepository;

    //Constructor
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }


    // **** METODOS CRUD ****
    //===========================

    //Crear una categoría NO PUEDEN HABER CATEGORIAS CON NOMBRES REPETIDOS
    public Categoria crearCategoria(Categoria categoria) throws BadRequestException{
        if (categoriaRepository.existsByNombre(categoria.getNombre())){
            throw new BadRequestException("Ya existe una categoria con el nombre " + categoria.getNombre());
        }

        return categoriaRepository.save(categoria);
    }

    //Listar todas las categorías
    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }
    
    //AL USAR EXCEPCIONES NO PUEDE DEVOLVER UN OPTIONAL ! DEVUELVE LA CATEGORIA O LA EXCEPCION
    public Categoria obtenerCategoriaPorId(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("Categoria con id " + id + " no encontrada"));
    }

    //Borrar categoria POR ID
    public void eliminarCategoriaPorId(Long id){
        //Antes de intentar borrar algo, conviene saber si existe
        if(!categoriaRepository.existsById(id)){
            throw new RecursoNoEncontradoException("Categoria con id " + id + " no existe");
        }
        categoriaRepository.deleteById(id);
    }

    


    //Actualizar una categoria SOLO EL NOMBRE


    

    //BORRAR CATEGORIA POR NOMBRE????

}
