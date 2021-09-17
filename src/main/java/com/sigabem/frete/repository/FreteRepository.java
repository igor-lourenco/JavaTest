package com.sigabem.frete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigabem.frete.model.Frete;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {
	
}
