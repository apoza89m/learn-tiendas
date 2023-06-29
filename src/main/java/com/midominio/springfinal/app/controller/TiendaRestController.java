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

import com.midominio.springfinal.app.model.Tienda;
import com.midominio.springfinal.app.service.TiendaService;


@RestController
@RequestMapping("/api")
public class TiendaRestController {
	
	@Autowired
	TiendaService service;
	
	@GetMapping("/tiendas")
	public List<Tienda> listarTiendas(){
		return service.listar();
	}
	
	@GetMapping("/tienda/{id}")
	public Tienda listarTienda(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@DeleteMapping("/tienda/{id}")
	public void borrarTienda(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PostMapping("/tienda")
	public Tienda crearTienda(@RequestBody Tienda tienda) {
	    return service.guardar(tienda);
	}

	@PutMapping("/tienda")
	public Tienda actualizarTienda(@RequestBody Tienda tienda) {
	    return service.guardar(tienda);
	}

}
