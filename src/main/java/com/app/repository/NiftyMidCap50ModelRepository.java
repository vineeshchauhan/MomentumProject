package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NiftyMidCap50Model;

public interface NiftyMidCap50ModelRepository extends JpaRepository<NiftyMidCap50Model, Integer> {

	List<NiftyMidCap50Model> findAll();
	
	NiftyMidCap50Model findByEquitySymbolId(Integer equitySymbolId);
	
}
