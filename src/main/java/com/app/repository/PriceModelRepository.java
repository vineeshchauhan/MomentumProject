package com.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.PriceModel;

public interface PriceModelRepository extends JpaRepository<PriceModel, Integer> {

	PriceModel findByEquitySymbolId(Integer equitySymbolId);
	
	PriceModel findByEquitySymbolIdAndDate(Integer equitySymbolId, LocalDate date);
	
	List<PriceModel> findByDate(LocalDate date);

}
