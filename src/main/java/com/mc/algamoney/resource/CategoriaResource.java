package com.mc.algamoney.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mc.algamoney.model.Categoria;
import com.mc.algamoney.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> listarTodos() {	
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> cadastrar(@RequestBody Categoria categoria, HttpServletResponse response) {	
		Categoria c = categoriaRepository.save(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(c.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).body(c);
	} 
	
	@GetMapping("/{codigo}")
	public Categoria buscarPeloCodigo(@PathVariable long codigo) {
		return categoriaRepository.findById(codigo).get();
	}

}
