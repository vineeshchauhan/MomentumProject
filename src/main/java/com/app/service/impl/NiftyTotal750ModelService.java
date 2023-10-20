package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.NiftyTotal750Model;
import com.app.repository.NiftyTotal750ModelRepository;

@Component
public class NiftyTotal750ModelService {

	@Autowired
	NiftyTotal750ModelRepository niftyTotal750ModelRepository;

	public List<NiftyTotal750Model> findAll() {

		return niftyTotal750ModelRepository.findAll();
	}
	
	public NiftyTotal750Model findByEquitySymbolId(Integer equitySymbolId) {

		return niftyTotal750ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public NiftyTotal750Model save(NiftyTotal750Model nifty50Model) {

		return niftyTotal750ModelRepository.save(nifty50Model);
	}
	
	public List<NiftyTotal750Model> saveAll(List<NiftyTotal750Model> it) {

		return niftyTotal750ModelRepository.saveAll(it);
	}
}
