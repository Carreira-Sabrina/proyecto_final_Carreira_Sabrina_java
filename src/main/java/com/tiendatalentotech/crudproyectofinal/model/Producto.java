package com.tiendatalentotech.crudproyectofinal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    private String descripcion;
    private String urlImagen;
    
    //POR EL MOMENTO LA RELACION ES UNIDIRECCIONAL  MUCHOS PRODUCTOS ➡ PERTENECEN A UNA CATEGORIA
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    public Producto(){}

    public Producto(Categoria categoria, String descripcion, String nombre, double precio, String urlImagen) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    public Producto(Long id, String nombre, double precio, String descripcion, String urlImagen, Categoria categoria){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.categoria = categoria;
    }

    

    //Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setUrlImagen(String url){
        this.urlImagen = url;
    }

    public String getUrlImagen(){
        return this.urlImagen;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
