package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.NiftyNext50Model;
import com.app.repository.NiftyNext50ModelRepository;

@Component
public class NiftyNext50ModelService {

	@Autowired
	NiftyNext50ModelRepository niftyNext50ModelRepository;

	public List<NiftyNext50Model> findAll() {

		return niftyNext50ModelRepository.findAll();

	}

	public NiftyNext50Model save(NiftyNext50Model model) {

		return niftyNext50ModelRepository.save(model);
	}
	
	public NiftyNext50Model findByEquitySymbolId(Integer equitySymbolId) {

		return niftyNext50ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public List<NiftyNext50Model> saveAll(List<NiftyNext50Model> it) {

		return niftyNext50ModelRepository.saveAll(it);
	}
}
