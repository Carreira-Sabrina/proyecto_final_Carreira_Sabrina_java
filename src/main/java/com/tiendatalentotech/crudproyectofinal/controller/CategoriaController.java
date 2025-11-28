package com.tiendatalentotech.crudproyectofinal.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) throws BadRequestException{

        //ACA YO QUIERO DEVOLVER EL ERROR COMO JSON TAMBIEN, COMO HAGO? 🦜🦜🦜🦜🦜

        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);

        return ResponseEntity.status(HttpStatus.OK).body(nuevaCategoria);
    }

    //LISTAR TODAS LAS CATEGORIAS (Para mi queda mejor devolver un ResponseEntity)
    @GetMapping() 
    public ResponseEntity<List<Categoria>> listarCategorias(){
        List<Categoria> listaCategorias= categoriaService.listarCategorias();

        return ResponseEntity.status(HttpStatus.OK).body(listaCategorias);
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

    //BORRAR CATEGORIA POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoriaPorId(@PathVariable Long id){
        categoriaService.eliminarCategoriaPorId(id);

        return ResponseEntity.noContent().build();
    }

    //ACTUALIZAR CATEGORIA
    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        
        return categoriaService.actualizarCategoria(id, categoria);
    }
}
