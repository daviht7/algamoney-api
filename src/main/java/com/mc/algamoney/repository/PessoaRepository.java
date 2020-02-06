package com.mc.algamoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.algamoney.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

}
