package com.midominio.springfinal.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.model.Tienda;
import com.midominio.springfinal.app.service.TiendaService;
import com.midominio.springfinal.app.utils.paginator.PageRender;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tiendas")
@SessionAttributes("tienda")
public class TiendaController {
	
	
	@Autowired
	TiendaService tiendaService;
	
	@GetMapping("/listar")
	public String listar(@RequestParam(defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Tienda> tiendas = tiendaService.listar(pageRequest);
		PageRender<Tienda> pageRender = new PageRender<>("/tiendas/listar", tiendas);

		model.addAttribute("titulo", "Listado de tiendas");
		model.addAttribute("tiendas", tiendas);
		model.addAttribute("page", pageRender);
		return "tiendas/listar";		
	}
	
	
	
	
	// [<<<---START] 
	// Controlador para ver individualmente una tienda con los pedidos
	@GetMapping(value = "/ver/{id}")
	@Transactional
	public String ver(@PathVariable(value = "id") Long id, 
			Map<String, Object> model, 
			RedirectAttributes flash) {

		Tienda tienda = tiendaService.findById(id);
		if (tienda == null) {
			flash.addFlashAttribute("error", "La tienda no existe en la base de datos");
			return "redirect:/tiendas/listar";
		}
		model.put("tienda", tienda);
		model.put("pedidos", tienda.getPedidos());
		model.put("titulo", "Información de la tienda: " + tienda.getNombre());
		return "tiendas/ver";
	}
	// [END--->>>]
	
	@GetMapping("/id/{id}")
	public String listarPorId(@PathVariable Long id, Model model) {
		Pageable pageRequest = PageRequest.of(0, 1); // Página 0 y cada página tiene 1
		Page<Tienda> tiendas = tiendaService.findById(pageRequest, id);
		PageRender<Tienda> pageRender = new PageRender<>("/articulos/id" + id, tiendas);
		model.addAttribute("titulo", "Listado de tiendas");
		model.addAttribute("tiendas", tiendas);
		model.addAttribute("page", pageRender);
		return "tiendas/listar";		
	}
	
	@GetMapping("/tipo/{tipo}")
	public String listarPorTipo(@PathVariable String tipo, @RequestParam(defaultValue = "0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Tienda> tiendas = tiendaService.findByTipo(pageRequest, tipo);
		PageRender<Tienda> pageRender = new PageRender<>("/tiendas/tipo/" + tipo, tiendas);
		model.addAttribute("titulo", "Listado de tiendas");
		model.addAttribute("tiendas", tiendas);
		model.addAttribute("page", pageRender);
		
		return "tiendas/listar";		
	}
	
	
	@GetMapping("/borrar/{id}")
	public String borrarPorId(@PathVariable Long id, Model model, RedirectAttributes flash) {
		model.addAttribute("titulo", "Listado de tiendas");
		tiendaService.delete(id);
		model.addAttribute("tiendas", tiendaService.listar());
		flash.addFlashAttribute("warning", "Tienda borrada con éxito");
		return "redirect:/tiendas/listar";		
	}	
	
	@GetMapping("/editar")
	public String formGet(Model model) {
		model.addAttribute("titulo", "Inserción de una tienda");
		model.addAttribute("tienda", new Tienda());
		return "tiendas/form";
	}
	
	@GetMapping("/editar/{id}")
	public String formGetById(@PathVariable Long id, Model model) {
		model.addAttribute("titulo", "Edición de una tienda");
		model.addAttribute("tienda", tiendaService.findById(id));
		return "tiendas/form";
	}
	
	@PostMapping("/form")
	public String guardar(@Valid Tienda tienda, BindingResult result, Model model) {  
		// la anotación para que se habiliten las validaciones
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Edición de una tienda");
			return "tiendas/form"; 
		}
		tiendaService.save(tienda);
		return "redirect:listar";
	}
	
}
