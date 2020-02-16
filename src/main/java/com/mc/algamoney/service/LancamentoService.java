package com.mc.algamoney.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.algamoney.model.Lancamento;
import com.mc.algamoney.model.Pessoa;
import com.mc.algamoney.repository.LancamentoRepository;
import com.mc.algamoney.repository.PessoaRepository;
import com.mc.algamoney.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	

	public Lancamento save(Lancamento lancamento) {
		
		Optional<Pessoa> p = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		
		if(p == null || p.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
			
		return lancamentoRepository.save(lancamento);
	
	}

}
