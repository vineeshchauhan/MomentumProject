package com.app.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.PriceModel;
import com.app.repository.PriceModelRepository;

@Component
public class PriceModelService {

	@Autowired
	PriceModelRepository priceModelRepository;

	public PriceModel saveOrUpdate(PriceModel model) {

		PriceModel savedModel = priceModelRepository.findByEquitySymbolIdAndDate(model.getEquitySymbolId(),
				model.getDate());

		if (savedModel == null) {
			return priceModelRepository.save(model);
		}
		return savedModel;

	}

	public List<PriceModel> findAll() {
		return priceModelRepository.findAll();
	}

	public List<PriceModel> findByDate(LocalDate date) {
		return priceModelRepository.findByDate(date);
	}

}
