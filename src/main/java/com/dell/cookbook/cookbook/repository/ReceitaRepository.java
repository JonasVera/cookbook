package com.dell.cookbook.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dell.cookbook.cookbook.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
}
