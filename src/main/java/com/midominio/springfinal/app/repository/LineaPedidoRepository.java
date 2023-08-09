package com.midominio.springfinal.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.model.LineaPedido;

public interface LineaPedidoRepository extends PagingAndSortingRepository<LineaPedido, Long>, CrudRepository<LineaPedido, Long>{
	
    List<LineaPedido> findByArticulo(Articulo articulo);

}

