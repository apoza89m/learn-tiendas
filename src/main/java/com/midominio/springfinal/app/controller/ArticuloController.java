package com.midominio.springfinal.app.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.midominio.springfinal.app.model.Articulo;
import com.midominio.springfinal.app.service.ArticuloService;
import com.midominio.springfinal.app.utils.paginator.PageRender;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {

	@Autowired
	ArticuloService articuloService;

	/*
	 * 
	 * Método handler
	 * 
	 * @ResponseBody
	 * 
	 * @GetMapping("/listar-rest") public List<Articulo> listarRest(){ return
	 * articuloService.listar(); }
	 */

	@GetMapping("/listar")
	public String listar(@RequestParam(defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Articulo> articulos = articuloService.listar(pageRequest);
		PageRender<Articulo> pageRender = new PageRender<>("/articulos/listar", articulos);
		
		model.addAttribute("titulo", "Listado de artículos");
		model.addAttribute("articulos", articulos);
		model.addAttribute("page", pageRender);
		return "articulos/listar";
	}

	@GetMapping("/editar")
	public String formGet(Model model) {
		model.addAttribute("titulo", "Inserción de una artículo");
		model.addAttribute("articulo", new Articulo());
		return "articulos/form";
	}

	@GetMapping("/id/{id}")
	public String listarPorId(@PathVariable Long id, Model model) {
		Pageable pageRequest = PageRequest.of(0, 1);  // Página 0 y cada página tiene 1
		Page<Articulo> articulos = articuloService.findById(pageRequest, id);
		PageRender<Articulo> pageRender = new PageRender<>("/articulos/id/" + id, articulos);
		
		model.addAttribute("titulo", "Listado de Artículos");
		model.addAttribute("articulos", articulos);
		model.addAttribute("page", pageRender);
		return "articulos/listar";
	}

	@GetMapping("/tipo/{tipo}")
	public String listarporTipo(@PathVariable String tipo, @RequestParam(defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Articulo> articulos = articuloService.findByTipo(pageRequest, tipo);
		PageRender<Articulo> pageRender = new PageRender<>("/articulos/tipo/" + tipo, articulos);

		model.addAttribute("titulo", "Listado de Artículos");
		model.addAttribute("articulos", articulos);
		model.addAttribute("page", pageRender);
		return "articulos/listar";
	}

	@GetMapping("/marca/{marca}")
	public String listarPorMarca(@PathVariable String marca, @RequestParam(defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Articulo> articulos = articuloService.findByMarca(pageRequest, marca);
		PageRender<Articulo> pageRender = new PageRender<>("/articulos/marca/" + marca, articulos);

		model.addAttribute("titulo", "Listado de Artículos");
		model.addAttribute("articulos", articulos);
		model.addAttribute("page", pageRender);
		return "articulos/listar";
	}

	@GetMapping("/borrar/{id}")
	public String listarPor(@PathVariable Long id, Model model, RedirectAttributes flash) {
		model.addAttribute("titulo", "Listado de Artículos");
		articuloService.delete(id);
		model.addAttribute("articulos", articuloService.listar());
		flash.addFlashAttribute("warning", "Artículo borrado con éxito");
		return "redirect:/articulos/listar";
	}

	@GetMapping("/editar/{id}")
	public String formGetById(@PathVariable Long id, Model model) {
		model.addAttribute("titulo", "Edición de un articulo");
		model.addAttribute("articulo", articuloService.findById(id));
		return "articulos/form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Articulo articulo, 
			BindingResult result, 
			// @RequestParam("file") MultipartFile foto,   eliminado para no meter foto
			Model model, RedirectAttributes flash) {
		// la anotación para que se habiliten las validaciones
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Edición de un artículo");
			return "articulos/form";
		}

		/* 
		 * De momento dejo la fotografía fuera, no me gusta la solución
		if (!foto.isEmpty()) {
			String rootPath = "/opt/upload";
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "/" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				flash.addFlashAttribute("info", "Subido correctamente " + foto.getOriginalFilename());
				articulo.setFoto(foto.getOriginalFilename());
			} catch (Exception e) {

			}
		}
		*/ 

		articuloService.save(articulo);
		flash.addFlashAttribute("success", "Artículo guardado con éxito");
		return "redirect:listar";

	}

	@GetMapping("/ver/{id}")
	public String verPorId(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, Model model,
			RedirectAttributes flash) {

		Articulo articulo = articuloService.findById(id);

		if (articulo == null) {
			flash.addFlashAttribute("error", "Articulo inexistente");
			return "redirect:/articulos/listar";
		}

		model.addAttribute("titulo", "Mostrando un Artículo");
		model.addAttribute("articulo", articulo);

		return "articulos/ver";
	}

}
