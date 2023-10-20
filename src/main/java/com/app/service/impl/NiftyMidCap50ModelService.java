package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.NiftyMidCap50Model;
import com.app.repository.NiftyMidCap50ModelRepository;

@Component
public class NiftyMidCap50ModelService {

	@Autowired
	NiftyMidCap50ModelRepository niftyMidCap50ModelRepository;

	public List<NiftyMidCap50Model> findAll() {

		return niftyMidCap50ModelRepository.findAll();

	}

	public NiftyMidCap50Model save(NiftyMidCap50Model model) {

		return niftyMidCap50ModelRepository.save(model);
	}
	
	public NiftyMidCap50Model findByEquitySymbolId(Integer equitySymbolId) {

		return niftyMidCap50ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public List<NiftyMidCap50Model> saveAll(List<NiftyMidCap50Model> it) {

		return niftyMidCap50ModelRepository.saveAll(it);
	}
}
