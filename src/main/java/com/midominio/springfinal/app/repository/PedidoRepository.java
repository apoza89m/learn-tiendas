package com.midominio.springfinal.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.midominio.springfinal.app.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
