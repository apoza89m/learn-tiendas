package com.midominio.springfinal.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.model.Tienda;

public interface TiendaService {
	List<Tienda> listar();
	Page<Tienda> listar(Pageable pageable);
	Tienda findById (Long id);
	Page<Tienda> findById(Pageable pageable, Long id);
	List<Tienda> findByTipo(String tipo);
	Page<Tienda> findByTipo(Pageable pageable, String tipo);
	void delete(Long id);
	void save(Tienda tienda);
	Tienda guardar(Tienda tienda);	
}