package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Nifty50Model;

public interface Nifty50ModelRepository extends JpaRepository<Nifty50Model, Integer> {

	List<Nifty50Model> findAll();
	
	Nifty50Model findByEquitySymbolId(Integer equitySymbolId);
	
}
