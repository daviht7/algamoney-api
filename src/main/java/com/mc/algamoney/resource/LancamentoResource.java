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
import com.mc.algamoney.model.Lancamento;
import com.mc.algamoney.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Lancamento> cadastrar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {	
		
		Lancamento c = lancamentoRepository.save(lancamento);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, c.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	} 
	
	@GetMapping("/{codigo}")
	public Lancamento buscarPeloCodigo(@PathVariable long codigo) {
		return lancamentoRepository.findById(codigo).get();
	}
	
	@GetMapping
	public List<Lancamento> buscarTodos() {
		return lancamentoRepository.findAll();
	}

}
