package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.EquitySymbolModel;

public interface EquitySymbolRepository extends JpaRepository<EquitySymbolModel, Integer> {

	EquitySymbolModel findByEquitySymbol(String symbol);
	
	EquitySymbolModel findByEquitySymbolId(Integer equitySymbolId);
	
}
