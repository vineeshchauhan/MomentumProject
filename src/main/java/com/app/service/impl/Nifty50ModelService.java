package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.Nifty50Model;
import com.app.repository.Nifty50ModelRepository;

@Component
public class Nifty50ModelService {

	@Autowired
	Nifty50ModelRepository nifty50ModelRepository;

	public List<Nifty50Model> findAll() {

		return nifty50ModelRepository.findAll();
	}
	
	public Nifty50Model findByEquitySymbolId(Integer equitySymbolId) {

		return nifty50ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public Nifty50Model save(Nifty50Model nifty50Model) {

		return nifty50ModelRepository.save(nifty50Model);
	}
	
	public List<Nifty50Model> saveAll(List<Nifty50Model> it) {

		return nifty50ModelRepository.saveAll(it);
	}
}
