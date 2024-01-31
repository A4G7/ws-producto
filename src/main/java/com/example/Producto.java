package com.example;

public class Producto {
	private Long id;
    private String nombre;
    private Integer stock;
    private String categoria;
    private Integer precio;
    private String descripcion;
	public Producto(Long id, String nombre, Integer stock, String categoria, Integer precio, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.categoria = categoria;
		this.precio = precio;
		this.descripcion = descripcion;
	}
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
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
}
