package com.tiendatalentotech.crudproyectofinal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendatalentotech.crudproyectofinal.exception.RecursoNoEncontradoException;
import com.tiendatalentotech.crudproyectofinal.model.Categoria;
import com.tiendatalentotech.crudproyectofinal.service.CategoriaService;




@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    //Constructor - Inyección de dependencias
    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }


    // ********** ENDPOINTS **********
    //_________________________________

    //CREAR CATEGORIA
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria){

        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);

        return ResponseEntity.status(201).body(nuevaCategoria);
    }

    //LISTAR TODAS LAS CATEGORIAS
    @GetMapping() 
    public List<Categoria> listarCategorias(){
        return categoriaService.listarCategorias();
    }

    //BUCAR CATEGORIA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCategoriaPorId(@PathVariable Long id){
        //Cuidado ! La ResponseEntity tiene que ser de Optional, porque es Categoria o Exception
        try {
            Categoria categoriaBuscada = categoriaService.obtenerCategoriaPorId(id);
            return ResponseEntity.ok(categoriaBuscada);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
