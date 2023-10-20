package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NiftyMidCap100Model;

public interface NiftyMidCap100ModelRepository extends JpaRepository<NiftyMidCap100Model, Integer> {

	List<NiftyMidCap100Model> findAll();
	
	NiftyMidCap100Model findByEquitySymbolId(Integer equitySymbolId);
}
