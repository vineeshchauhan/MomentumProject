package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.EquitySymbolModel;
import com.app.repository.EquitySymbolRepository;

@Component
public class EquitySymbolService {

	@Autowired
	EquitySymbolRepository equitySymbolRepository;

	public EquitySymbolModel saveOrUpdate(EquitySymbolModel model) {

		EquitySymbolModel savedModel = equitySymbolRepository.findByEquitySymbol(model.getEquitySymbol());

		if (savedModel == null) {
			return equitySymbolRepository.save(model);
		}
		return savedModel;

	}
	
	public List<EquitySymbolModel> findAll() {
		return equitySymbolRepository.findAll();
	}
	
	public EquitySymbolModel findByEquitySymbolId(Integer equitySymbolId) {
		return equitySymbolRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public EquitySymbolModel findByEquitySymbol(String equitySymbol) {
		return equitySymbolRepository.findByEquitySymbol(equitySymbol);
	}
	
}
