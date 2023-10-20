package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NiftySmallCap250Model;

public interface NiftySmallCap250ModelRepository extends JpaRepository<NiftySmallCap250Model, Integer> {

	List<NiftySmallCap250Model> findAll();
	
	NiftySmallCap250Model findByEquitySymbolId(Integer equitySymbolId);
}
