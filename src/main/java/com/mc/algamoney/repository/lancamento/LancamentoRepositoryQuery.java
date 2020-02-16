package com.mc.algamoney.repository.lancamento;

import java.util.List;

import com.mc.algamoney.model.Lancamento;
import com.mc.algamoney.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
	

}
