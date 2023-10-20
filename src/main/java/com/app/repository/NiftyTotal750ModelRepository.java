package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NiftyTotal750Model;

public interface NiftyTotal750ModelRepository extends JpaRepository<NiftyTotal750Model, Integer> {

	List<NiftyTotal750Model> findAll();
	
	NiftyTotal750Model findByEquitySymbolId(Integer equitySymbolId);
	
}
