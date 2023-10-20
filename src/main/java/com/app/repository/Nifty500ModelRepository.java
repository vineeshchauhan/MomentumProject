package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Nifty500Model;

public interface Nifty500ModelRepository extends JpaRepository<Nifty500Model, Integer> {

	List<Nifty500Model> findAll();
	
	Nifty500Model findByEquitySymbolId(Integer equitySymbolId);
	
}
