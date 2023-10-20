package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NiftySmallCap50Model;

public interface NiftySmallCap50ModelRepository extends JpaRepository<NiftySmallCap50Model, Integer> {

	List<NiftySmallCap50Model> findAll();
	
	NiftySmallCap50Model findByEquitySymbolId(Integer equitySymbolId);
	
}
