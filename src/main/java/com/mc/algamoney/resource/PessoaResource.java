package com.mc.algamoney.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mc.algamoney.event.ResourceCreatedEvent;
import com.mc.algamoney.model.Pessoa;
import com.mc.algamoney.repository.PessoaRepository;
import com.mc.algamoney.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public List<Pessoa> listarTodos() {	
		return pessoaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {	
		Pessoa c = pessoaRepository.save(pessoa);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, c.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	} 
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long codigo) {	
		pessoaRepository.deleteById(codigo);
	} 
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		
		return ResponseEntity.ok(pessoaService.atualizar(codigo, pessoa));
		
	}
	

}
	