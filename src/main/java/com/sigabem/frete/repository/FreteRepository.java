package com.sigabem.frete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sigabem.frete.model.Frete;

public interface FreteRepository extends JpaRepository<Frete, Long> {
	
}
