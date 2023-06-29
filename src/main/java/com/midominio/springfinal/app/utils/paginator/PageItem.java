package com.midominio.springfinal.app.utils.paginator;

public class PageItem {
	
	// Representa una página
	
	private int numero;       // número de página
	private boolean actual;   // si es la página actual 
	
	// Constructor estándar y sus getters
	
	public PageItem(int numero, boolean actual) {
		this.numero = numero;
		this.actual = actual;
	}
	
	
	public int getNumero() {
		return numero;
	}
	public boolean isActual() {
		return actual;
	}
}
