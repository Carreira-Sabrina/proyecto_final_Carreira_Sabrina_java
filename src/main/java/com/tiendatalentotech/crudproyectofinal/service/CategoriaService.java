package com.tiendatalentotech.crudproyectofinal.service;

import java.util.List;

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

    //Crear una categoría
    public Categoria crearCategoria(Categoria categoria){
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

    //Actualizar una categoria SOLO EL NOMBRE


    //Borrar categoria POR ID

    //BORRAR CATEGORIA POR NOMBRE????

}
