package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NiftyMicroCap250Model;

public interface NiftyMicroCap250ModelRepository extends JpaRepository<NiftyMicroCap250Model, Integer> {

	List<NiftyMicroCap250Model> findAll();
	
	NiftyMicroCap250Model findByEquitySymbolId(Integer equitySymbolId);
	
}
