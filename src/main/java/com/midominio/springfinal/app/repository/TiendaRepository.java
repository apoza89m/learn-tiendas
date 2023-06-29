package com.midominio.springfinal.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.midominio.springfinal.app.model.Tienda;

public interface TiendaRepository extends PagingAndSortingRepository<Tienda, Long>, CrudRepository<Tienda, Long>{
	
    List<Tienda> findByTipo(String tipo);
    Page<Tienda> findByTipo(Pageable pageable, String tipo);
    Page<Tienda> findById(Pageable pageable, Long id);

}

