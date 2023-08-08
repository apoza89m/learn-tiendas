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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tiendas")
@Getter @Setter
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
	
	@OneToMany(mappedBy = "tienda" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//mappedBy -> relacion bidireccional
	private List<Pedido> pedidos;
	
	// Constructor vacío de la tienda inicializa la lista de pedidos (en blanco)
	public Tienda() {pedidos = new ArrayList<Pedido>();}
	
	// Método para añadir pedido  a la tienda (añade elemento a la lista de pedidos)
	public void addPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}
