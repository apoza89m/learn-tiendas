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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedidos")
@Getter @Setter
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String referencia;
	private	String descripcion;

	@ManyToOne(fetch = FetchType.EAGER)  // Carga NO perezosa
	private Tienda tienda;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//cascade -> si se borra un pedido, borra todas sus lineas
	@JoinColumn(name="lineas_pedidos_id")
	private List<LineaPedido> lineas;
	
	public Pedido() {lineas = new ArrayList<LineaPedido>();}
	
	public void addLineaPedido(LineaPedido lineaPedido) {
		lineas.add(lineaPedido);
	}
	
	public double getPrecioTotal() {
		double total = 0;
		
		for (LineaPedido lineaPedido : lineas) {
			total += lineaPedido.calcularPrecio();
		}
		return total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}