package com.midominio.springfinal.app.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.model.LineaPedido;
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
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, 
			Model model,
			RedirectAttributes flash) {
		Pedido pedido = tiendaService.findPedidoById(id);
		
		if(pedido == null) {
			flash.addFlashAttribute("error", "El pedido no existe en la base de datos!");
			return "redirect:/tienda/listar";
		}
		
		model.addAttribute("pedido", pedido);
		model.addAttribute("titulo", "Pedido: ".concat(pedido.getDescripcion()));
		
		return "pedido/ver";
	}
	
	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable("clienteId") Long clienteId,
			Map<String, Object> model, 
			RedirectAttributes flash) {
		
		Tienda tienda = tiendaService.findById(clienteId);
		
		if (tienda == null) {
			flash.addFlashAttribute("error", "La tienda no existe en la base de datos");
			return "redirect:/listar";			
		} 
		
		Pedido pedido = new Pedido();
		pedido.setTienda(tienda);
		model.put("pedido", pedido);
		model.put("titulo", "Creación de pedido");	
		return "pedido/form";
	}
	
	// value tiene que ser igual que la url del js 
	@GetMapping(value="/buscar-articulos/{text}", produces= {"application/json"})
	public @ResponseBody List<Articulo> cargarArticulos(@PathVariable String text){
		log.info("Saludando");
		return articuloService.findByTipoText(text);
	}
	
	// Hay que recibir los datos de cada input del form.html	
	@PostMapping("/form")
	public String guardar(Pedido pedido,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, 
			RedirectAttributes flash, 
			SessionStatus status) {
			
		for (int i = 0; i < itemId.length; i++) {
			Articulo articulo = tiendaService.findArticuloById(itemId[i]);

			LineaPedido linea = new LineaPedido();
			linea.setCantidad(cantidad[i]);
			linea.setArticulo(articulo);
			pedido.addLineaPedido(linea);

			log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
		}
				
		tiendaService.guardarPedido(pedido);
		status.setComplete();

		flash.addFlashAttribute("success", "Pedido creado con éxito!");
		
		// Para mostrar detalle de tienda mostrando estado de sus pedidos
		return "redirect:/tiendas/ver/" + pedido.getTienda().getId();
	}	
	@GetMapping(value="/eliminar/{id}")
	public String borrarPedido(@PathVariable Long id , 
			RedirectAttributes flash) {
		Pedido pedido = tiendaService.findPedidoById(id);
		if (pedido != null) {
			tiendaService.deletePedido(pedido);
			flash.addFlashAttribute("success", "Pedido eliminado con éxito");
			return "redirect:/tiendas/ver/" + pedido.getTienda().getId();
		}
		flash.addFlashAttribute("errors", "El pedido no existe y no puede borrarse");
		return "redirect:/tiendas/ver/" + pedido.getTienda().getId();
	}
}
