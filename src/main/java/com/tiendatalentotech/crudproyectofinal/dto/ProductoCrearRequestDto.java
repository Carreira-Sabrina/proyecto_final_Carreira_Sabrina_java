package com.tiendatalentotech.crudproyectofinal.dto;

public record ProductoCrearRequestDto(Long id, String nombre, double precio, Long idCategoria, String descripcion, String urlImagen) {

}
