package com.midominio.springfinal.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.model.LineaPedido;
import com.midominio.springfinal.app.repository.ArticuloRepository;
import com.midominio.springfinal.app.repository.LineaPedidoRepository;


@Service
public class LineaPedidoServiceImpl implements LineaPedidoService {
	
	@Autowired
	LineaPedidoRepository repository;

		
	@Override
	@Transactional(readOnly = true)
	public List<LineaPedido> findByArticulo(Articulo articulo) {
		return repository.findByArticulo(articulo);		
	}
}
