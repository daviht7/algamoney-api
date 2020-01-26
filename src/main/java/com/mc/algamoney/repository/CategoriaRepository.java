package com.mc.algamoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.algamoney.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
