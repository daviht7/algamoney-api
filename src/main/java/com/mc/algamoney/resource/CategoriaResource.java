package com.mc.algamoney.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.algamoney.event.ResourceCreatedEvent;
import com.mc.algamoney.model.Categoria;
import com.mc.algamoney.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Categoria> listarTodos() {	
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {	
		
		Categoria c = categoriaRepository.save(categoria);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, c.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	} 
	
	@GetMapping("/{codigo}")
	public Categoria buscarPeloCodigo(@PathVariable long codigo) {
		return categoriaRepository.findById(codigo).get();
	}

}
