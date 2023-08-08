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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lineas_pedidos")
@Getter @Setter
public class LineaPedido implements Serializable {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="articulos_id")
	private Articulo articulo;
	

	// Calcula el precio de la línea a partir del precio del artículo y la cantidad	
	public double calcularPrecio() {
		return cantidad * articulo.getPrecio();
	}
	// [END--->>>]	
}
