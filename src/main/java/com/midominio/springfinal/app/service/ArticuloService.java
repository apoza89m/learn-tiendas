package com.midominio.springfinal.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.midominio.springfinal.app.model.Articulo;

public interface ArticuloService {
	List<Articulo> listar(); 
	Page<Articulo> listar(Pageable pageable);
	Articulo findById (Long id);
	Page<Articulo> findById (Pageable pageable, Long id);
	List<Articulo> findByTipo(String tipo);
	Page<Articulo> findByTipo(Pageable pageable, String tipo);
	List<Articulo> findByMarca(String marca);
	Page<Articulo> findByMarca(Pageable pageable, String marca);
	void delete(Long id);
	void save(Articulo articulo);
	Articulo guardar(Articulo articulo);
}
