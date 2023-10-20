package com.app.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.HighLowPriceModel;

public interface HighLowPriceModelRepository extends JpaRepository<HighLowPriceModel, Integer> {

	HighLowPriceModel findByEquitySymbolId(Integer equitySymbolId);
	
	HighLowPriceModel findByEquitySymbolIdAndDate(Integer equitySymbolId, LocalDate date);

}
