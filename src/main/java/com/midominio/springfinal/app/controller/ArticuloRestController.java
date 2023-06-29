package com.midominio.springfinal.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.service.ArticuloService;

@RestController
@RequestMapping("/api")
public class ArticuloRestController {
	
	@Autowired
	ArticuloService service;
	
	@GetMapping("/articulos")
	public List<Articulo> listarArticulos(){
		return service.listar();
	}
	
	@GetMapping("/articulo/{id}")
	public Articulo listarArticulo(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@DeleteMapping("/articulo/{id}")
	public void borrarArticulo(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PostMapping("/articulo")
	public Articulo crearArticulo(@RequestBody Articulo articulo) {
	    return service.guardar(articulo);
	}

	@PutMapping("/articulo")
	public Articulo actualizarArticulo(@RequestBody Articulo articulo) {
	    return service.guardar(articulo);
	}

}
