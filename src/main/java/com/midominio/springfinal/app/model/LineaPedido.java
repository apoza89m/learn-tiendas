package com.midominio.springfinal.app.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lineas_pedidos")
public class LineaPedido implements Serializable {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	private Integer precio;

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}



	// [<<<---START]
	// RELACION LINEA DE PEDIDO Y ARTICULO MUCHOS A 1 (UNIDIRECCIONAL LINEA -> ARTICULO)  	
	// Artículo de la línea
	// El enlace se produce utilizando el campo tienda de la clase Pedido y es bidireccional
	// Descarga los elementos relacionados de forma perezosa
	// El borrado se produce en cascada
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="articulo_id")
	private Articulo articulo;
	
	// Calcula el precio de la línea a partir del precio del artículo y la cantidad	
	public double calcularPrecio() {
		return cantidad * articulo.getPrecio();
	}
	
	// Getter & Setter
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	// [END--->>>]	
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}
	
	
}
