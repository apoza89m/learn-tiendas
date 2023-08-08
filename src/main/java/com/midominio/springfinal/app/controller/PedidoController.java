package com.midominio.springfinal.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.model.Pedido;
import com.midominio.springfinal.app.model.Tienda;
import com.midominio.springfinal.app.service.ArticuloService;
import com.midominio.springfinal.app.service.TiendaService;

@Controller
@RequestMapping("/pedido")
// el objeto pedido y sus relaciones estará activo en la sesión mientras no se cierre
@SessionAttributes("pedido") // mismo nombre atributo que se pasa a la vista 
public class PedidoController {
	
	@Autowired
	private TiendaService tiendaService;
	
	@Autowired
	private ArticuloService articuloService;
	
	@GetMapping("/form/{tiendaId}")
	public String crear(@PathVariable("tiendaId") Long tiendaId,
			Map<String, Object> model, 
			RedirectAttributes flash) {
		
		Tienda tienda = tiendaService.findById(tiendaId);
		
		if (tienda == null) {
			flash.addFlashAttribute("Error", "La tienda no existe en la base de datos");
			return "redirect:/listar";			
		} 
		
		Pedido pedido = new Pedido();
		pedido.setTienda(tienda);
		model.put("pedido", pedido);
		model.put("titulo", "Creación de pedido");	
		return "pedido/form";
	}
	@GetMapping(value="/buscar-articulos/{text}", produces= {"application/json"})
	public @ResponseBody List<Articulo> cargarArticulos(@PathVariable String text){
		return articuloService.findByTipoText(text);
	}
}