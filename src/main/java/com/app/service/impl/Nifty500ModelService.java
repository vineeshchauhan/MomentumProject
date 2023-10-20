package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.Nifty500Model;
import com.app.repository.Nifty500ModelRepository;

@Component
public class Nifty500ModelService {

	@Autowired
	Nifty500ModelRepository nifty50ModelRepository;

	public List<Nifty500Model> findAll() {

		return nifty50ModelRepository.findAll();
	}
	
	public Nifty500Model findByEquitySymbolId(Integer equitySymbolId) {

		return nifty50ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public Nifty500Model save(Nifty500Model nifty50Model) {

		return nifty50ModelRepository.save(nifty50Model);
	}
	
	public List<Nifty500Model> saveAll(List<Nifty500Model> it) {

		return nifty50ModelRepository.saveAll(it);
	}
}
