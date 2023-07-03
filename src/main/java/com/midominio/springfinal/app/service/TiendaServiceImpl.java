package com.midominio.springfinal.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.model.Pedido;
import com.midominio.springfinal.app.model.Tienda;
import com.midominio.springfinal.app.repository.ArticuloRepository;
import com.midominio.springfinal.app.repository.PedidoRepository;
import com.midominio.springfinal.app.repository.TiendaRepository;

@Service
public class TiendaServiceImpl implements TiendaService {
	
	@Autowired
	TiendaRepository tiendaRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ArticuloRepository articuloRepository;	

	@Override
	@Transactional(readOnly = true)
	public List<Tienda> listar() {
		return (List<Tienda>) tiendaRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Tienda> findById(Pageable pageable, Long id) {	
		return tiendaRepository.findById(pageable, id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Tienda findById(Long id) {		
		return tiendaRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Tienda> findByTipo(String tipo) {
		return tiendaRepository.findByTipo(tipo);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		tiendaRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public void save(Tienda tienda) {
		tiendaRepository.save(tienda);
	}
	
	@Override
	@Transactional
	public Tienda guardar(Tienda tienda) {
		return tiendaRepository.save(tienda);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tienda> findByTipo(Pageable pageable, String tipo) {
		return tiendaRepository.findByTipo(pageable, tipo);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tienda> listar(Pageable pageable) {
		return tiendaRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void guardarPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findArticuloById(Long id) {
		return articuloRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deletePedido(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}

	@Override
	@Transactional(readOnly = true)
	public Pedido findPedidoById(Long id) {	
		return pedidoRepository.findById(id).orElse(null);
	}
}
