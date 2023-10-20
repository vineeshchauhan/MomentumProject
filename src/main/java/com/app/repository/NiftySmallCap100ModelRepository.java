package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NiftySmallCap100Model;

public interface NiftySmallCap100ModelRepository extends JpaRepository<NiftySmallCap100Model, Integer> {

	List<NiftySmallCap100Model> findAll();
	
	NiftySmallCap100Model findByEquitySymbolId(Integer equitySymbolId);
	
}
