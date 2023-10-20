package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Nifty100Model;

public interface Nifty100ModelRepository extends JpaRepository<Nifty100Model, Integer> {

	List<Nifty100Model> findAll();
	
	Nifty100Model findByEquitySymbolId(Integer equitySymbolId);
	
}
