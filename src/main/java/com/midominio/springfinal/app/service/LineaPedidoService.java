package com.midominio.springfinal.app.service;

import java.util.List;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.model.LineaPedido;

public interface LineaPedidoService {
	
	public List<LineaPedido> findByArticulo(Articulo articulo);	
}
