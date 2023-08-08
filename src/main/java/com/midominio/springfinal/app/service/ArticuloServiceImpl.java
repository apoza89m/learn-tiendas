package com.midominio.springfinal.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.repository.ArticuloRepository;


@Service
public class ArticuloServiceImpl implements ArticuloService {
	
	@Autowired
	ArticuloRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> listar() {
		return (List<Articulo>) repository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Articulo findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Articulo> findById(Pageable pageable, Long id) {
		return repository.findById(pageable, id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findByTipo(String tipo) {
		return repository.findByTipo(tipo);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findByMarca(String marca) {
		// TODO Auto-generated method stub
		return repository.findByMarca(marca);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public void save(Articulo articulo) {
		repository.save(articulo);
	}
	
	@Override
	@Transactional
	public Articulo guardar(Articulo articulo) {
		return repository.save(articulo);
	}


	@Override
	@Transactional(readOnly = true)
	public Page<Articulo> listar(Pageable pageable) {
		return repository.findAll(pageable);
	}


	@Override
	@Transactional(readOnly = true)
	public Page<Articulo> findByTipo(Pageable pageable, String tipo) {
		return repository.findByTipo(pageable, tipo);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Articulo> findByMarca(Pageable pageable, String marca) {	
		return repository.findByMarca(pageable, marca);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findByTipoText(String text) {
		//return repository.findByTipoText(text);
		return repository.findByTipoLikeIgnoreCase("%" + text + "%");
	}

}
