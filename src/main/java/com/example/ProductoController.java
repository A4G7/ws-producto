package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpHeaders;
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

@RestController
@RequestMapping("api/producto")
public class ProductoController {
	private static final List<Producto> productos = new ArrayList<>();
	private final AtomicLong contador = new AtomicLong();
	
	public ProductoController() {
		initData();
	}
	
	public void initData() {
		Producto producto1 = new Producto(contador.incrementAndGet(), "Mouse", 5, "Electrónica", 20, "Dispositivo para manipular cosas en la pantalla.");
		productos.add(producto1);
		
        Producto producto2 = new Producto(contador.incrementAndGet(), "Teclado", 10, "Electrónica", 21, "Dispositivo que actua como interruptores que envia informacion a la computadora.");
        productos.add(producto2);
        
        Producto producto3 = new Producto(contador.incrementAndGet(), "Chaqueta", 15, "Ropa", 54, "Ropa que sirve para abrigar.");
        productos.add(producto3);
        
        Producto producto4 = new Producto(contador.incrementAndGet(), "Colchoneta", 24, "Deportes", 25, "Se usa para hacer ejercicios.");
        productos.add(producto4);
        
        Producto producto5 = new Producto(contador.incrementAndGet(), "Lego", 30, "Juguetes", 10, "Juguetes de costruccion para niños.");
        productos.add(producto5);
	}
	
	@GetMapping()
	public ResponseEntity<List<Producto>> listar(){
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	@GetMapping({"/{id}"})
	public ResponseEntity<Producto> obtener(@PathVariable long id){
		Producto p = productos.stream().filter(x -> id == x.getId()).findAny().orElse(null);
		if(p != null) {
			return new ResponseEntity<Producto>(p, HttpStatus.OK);
		}else {
			return new ResponseEntity<Producto>(p, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping()
	public ResponseEntity<Producto> registrar(@RequestBody Producto p){
		Producto producto = new Producto(contador.incrementAndGet(), p.getNombre(), p.getStock(), p.getCategoria(), p.getPrecio(), p.getDescripcion());
		productos.add(producto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(producto, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping({"/{id}"})
	public ResponseEntity<Producto> actualizar(@PathVariable long id, @RequestBody Producto p){
		Producto temp = null;
		for (Producto producto : productos) {
			if(producto.getId() == id) {
				producto.setNombre(p.getNombre());
				producto.setStock(p.getStock());
				producto.setCategoria(p.getCategoria());
				producto.setPrecio(p.getPrecio());
				producto.setDescripcion(p.getDescripcion());
				temp = producto;
				break;
			}
		}
		
		return new ResponseEntity<>(temp, HttpStatus.OK);
	}
	
	@DeleteMapping({"/{id}"})
	public ResponseEntity<Producto> eliminar(@PathVariable long id){
		Producto p = productos.stream().filter(x -> id == x.getId()).findAny().orElse(null);
		if(p != null) {
			productos.remove(p);
		}
		return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
	}
}
