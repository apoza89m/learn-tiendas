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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tiendas")
public class Tienda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Size(min = 3, max=15)
	private String nombre;
	@NotEmpty
	@Size(min = 3, max=20)
	private String tipo;
	
	@NotEmpty
	@Size(min = 3, max=100)
	private String calle;
	
	@NotEmpty
	@Size(min = 1, max=7)
	private String numero;
	
	@NotEmpty
	@Email
	private String email;
	
	// [<<<---START] 
	// == RELACION TIENDA Y PEDIDOS DE 1 A MUCHOS BIDIRECCIONAL ==  	
	// Lista de pedidos
	// El enlace se produce utilizando el campo tienda de la clase Pedido y es bidireccional
	// Descarga los elementos relacionados de forma perezosa
	// El borrado se produce en cascada
	@OneToMany(mappedBy = "tienda" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Pedido> pedidos;
	
	// Constructor vacío de la tienda inicializa la lista de pedidos (en blanco)
	public Tienda() {pedidos = new ArrayList<Pedido>();}
	
	// Método para añadir pedido  a la tienda (añade elemento a la lista de pedidos)
	public void addPedido(Pedido pedido) {
		pedidos.add(pedido);
	}
	
	// getter y setter de los pedidos
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	// [END--->>>]
	
	

	public Long getId() {
		return id;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
