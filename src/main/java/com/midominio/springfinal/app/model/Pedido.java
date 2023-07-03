package com.midominio.springfinal.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String referencia;
	private	String descripcion;
	
	// [<<<---START]
	// == RELACION TIENDA Y PEDIDOS DE 1 A MUCHOS BIDIRECCIONAL ==  	
	// Tienda
	// El enlace bidireccional ya se ha establecido como bidireccional en la clase Tienda
	// Descarga los elementos relacionados de forma perezosa
	// El borrado se produce en cascada
	
	@ManyToOne(fetch = FetchType.EAGER)  // Carga no perezosa
	private Tienda tienda;
	
	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	// [END--->>>]
	
	
	// [<<<---START]
	// RELACION PEDIDO Y LINEAS DE PEDIDO 1 A MUCHOS (UNIDIRECCIONAL PEDIDO -> LINEA) 	
	// Lista de pedidos
	// El enlace se produce utilizando el campo tienda de la clase Pedido y es bidireccional
	// Descarga los elementos relacionados de forma perezosa
	// El borrado se produce en cascada
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "pedido_id")
	private List<LineaPedido> lineas;
	
	// Getter & Setter
	public List<LineaPedido> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}
	
	// Constructor sin parámetros inicializa las lineas de pedido
	public Pedido() {lineas = new ArrayList<LineaPedido>();}

	
	// Método para añadir una línea de pedido a un pedido
	public void addLineaPedido(LineaPedido lineaPedido) {
		lineas.add(lineaPedido);
	}
	
	// Método para calcular el precio total de un pedido (todas las líneas de pedido)
	public double getPrecioTotal() {
		double total = 0;
		for(LineaPedido linea: lineas) {
			total +=linea.calcularPrecio();
		}
		return total;
	}
	// [END--->>>]	
	
	
	public String getReferencia() {
		return referencia;
	}



	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}
	

}
