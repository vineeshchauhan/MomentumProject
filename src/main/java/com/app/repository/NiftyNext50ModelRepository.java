package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NiftyNext50Model;

public interface NiftyNext50ModelRepository extends JpaRepository<NiftyNext50Model, Integer> {

	List<NiftyNext50Model> findAll();
	
	NiftyNext50Model findByEquitySymbolId(Integer equitySymbolId);
	
}
