package com.frete.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frete.entities.Frete;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long>{

}
