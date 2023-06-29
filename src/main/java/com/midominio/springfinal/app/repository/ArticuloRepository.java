package com.midominio.springfinal.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.midominio.springfinal.app.model.Articulo;

public interface ArticuloRepository extends PagingAndSortingRepository<Articulo, Long>, CrudRepository<Articulo, Long>{
	
    List<Articulo> findByTipo(String tipo);
    List<Articulo> findByMarca(String marca);
    Page<Articulo> findByTipo(Pageable pageable, String tipo);
    Page<Articulo> findByMarca(Pageable pageable, String tipo);
    Page<Articulo> findById(Pageable pageable, Long id);
}
