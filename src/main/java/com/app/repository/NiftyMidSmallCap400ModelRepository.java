package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NiftyMidSmallCap400Model;

public interface NiftyMidSmallCap400ModelRepository extends JpaRepository<NiftyMidSmallCap400Model, Integer> {

	List<NiftyMidSmallCap400Model> findAll();
	
	NiftyMidSmallCap400Model findByEquitySymbolId(Integer equitySymbolId);
	
}
