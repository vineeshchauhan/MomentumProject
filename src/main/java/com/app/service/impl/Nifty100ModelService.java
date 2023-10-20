package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.Nifty100Model;
import com.app.repository.Nifty100ModelRepository;

@Component
public class Nifty100ModelService {

	@Autowired
	Nifty100ModelRepository nifty100ModelRepository;

	public List<Nifty100Model> findAll() {

		return nifty100ModelRepository.findAll();
	}
	
	public Nifty100Model save(Nifty100Model model) {

		return nifty100ModelRepository.save(model);
	}
	
	public Nifty100Model findByEquitySymbolId(Integer equitySymbolId) {

		return nifty100ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public List<Nifty100Model> saveAll(List<Nifty100Model> it) {

		return nifty100ModelRepository.saveAll(it);
	}
}
