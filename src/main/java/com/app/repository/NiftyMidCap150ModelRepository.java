package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NiftyMidCap150Model;

public interface NiftyMidCap150ModelRepository extends JpaRepository<NiftyMidCap150Model, Integer> {

	List<NiftyMidCap150Model> findAll();
	
	NiftyMidCap150Model findByEquitySymbolId(Integer equitySymbolId);
	
}
