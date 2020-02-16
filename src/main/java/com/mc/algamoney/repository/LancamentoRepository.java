package com.mc.algamoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.algamoney.model.Lancamento;
import com.mc.algamoney.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>,LancamentoRepositoryQuery {

}
