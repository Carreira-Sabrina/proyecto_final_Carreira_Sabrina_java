package com.tiendatalentotech.crudproyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendatalentotech.crudproyectofinal.model.Categoria;



public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public boolean existsByNombre(String nombre);

}
