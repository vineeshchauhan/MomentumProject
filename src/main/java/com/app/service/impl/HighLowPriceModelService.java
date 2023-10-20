package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.HighLowPriceModel;
import com.app.repository.HighLowPriceModelRepository;

@Component
public class HighLowPriceModelService {
	@Autowired
	HighLowPriceModelRepository highLowPriceModelRepository;

	public HighLowPriceModel saveOrUpdate(HighLowPriceModel model) {

		HighLowPriceModel savedModel = highLowPriceModelRepository.findByEquitySymbolIdAndDate(model.getEquitySymbolId(), model.getDate());

		if (savedModel == null) {
			return highLowPriceModelRepository.save(model);
		}
		return savedModel;

	}
	
	public List<HighLowPriceModel> findAll() {
		return highLowPriceModelRepository.findAll();
	}
}
