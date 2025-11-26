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

import com.tiendatalentotech.crudproyectofinal.dto.ProductoCrearRequestDto;
import com.tiendatalentotech.crudproyectofinal.exception.RecursoNoEncontradoException;
import com.tiendatalentotech.crudproyectofinal.model.Producto;
import com.tiendatalentotech.crudproyectofinal.service.ProductoService;



@RestController
@RequestMapping("/api/producto")
public class ProductoController {


    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }


    //CREAR UN PRODUCTO
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoCrearRequestDto peticion){

        //AHORA ENTIENDO PORQUE USAN dto 🦜🦜🦜

        Producto nuevoProducto = productoService.crearProducto(peticion);

        return ResponseEntity.status(201).body(nuevoProducto);
    }

    //OBTENER TODOS LOS PRODUCTOS
    @GetMapping("path")
    public List<Producto> listarProductos(){
        return productoService.listarProductos();
    }

    //OBTENER PRODUCTO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id){

        try {
            Producto productoBuscado = productoService.obtenerProductoPorId(id);
            return ResponseEntity.ok(productoBuscado);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }




    




}
