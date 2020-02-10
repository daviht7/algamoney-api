package com.mc.algamoney.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mc.algamoney.model.Pessoa;
import com.mc.algamoney.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo,Pessoa pessoa) {
		
		Optional<Pessoa> p = pessoaRepository.findById(codigo);
		
		if(!p.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(pessoa,p.get(), "codigo");
		
		Pessoa pessoaSalva = pessoaRepository.save(p.get());
		
		return pessoaSalva;
		
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Optional<Pessoa> p = pessoaRepository.findById(codigo);
		
		if(!p.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		p.get().setAtivo(ativo);
		
		pessoaRepository.save(p.get());
		
	}
	

}
