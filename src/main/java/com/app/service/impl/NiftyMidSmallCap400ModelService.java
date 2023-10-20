package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.NiftyMidSmallCap400Model;
import com.app.repository.NiftyMidSmallCap400ModelRepository;

@Component
public class NiftyMidSmallCap400ModelService {

	@Autowired
	NiftyMidSmallCap400ModelRepository niftyMidSmallCap400ModelRepository;

	public List<NiftyMidSmallCap400Model> findAll() {

		return niftyMidSmallCap400ModelRepository.findAll();
	}
	
	public NiftyMidSmallCap400Model findByEquitySymbolId(Integer equitySymbolId) {

		return niftyMidSmallCap400ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public NiftyMidSmallCap400Model save(NiftyMidSmallCap400Model nifty50Model) {

		return niftyMidSmallCap400ModelRepository.save(nifty50Model);
	}
	
	public List<NiftyMidSmallCap400Model> saveAll(List<NiftyMidSmallCap400Model> it) {

		return niftyMidSmallCap400ModelRepository.saveAll(it);
	}
}
