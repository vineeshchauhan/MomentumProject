package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.NiftySmallCap50Model;
import com.app.repository.NiftySmallCap50ModelRepository;

@Component
public class NiftySmallCap50ModelService {

	@Autowired
	NiftySmallCap50ModelRepository niftySmallCap50ModelRepository;

	public List<NiftySmallCap50Model> findAll() {

		return niftySmallCap50ModelRepository.findAll();

	}
	
	public NiftySmallCap50Model save(NiftySmallCap50Model model) {

		return niftySmallCap50ModelRepository.save(model);
	}
	
	public NiftySmallCap50Model findByEquitySymbolId(Integer equitySymbolId) {

		return niftySmallCap50ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public List<NiftySmallCap50Model> saveAll(List<NiftySmallCap50Model> it) {

		return niftySmallCap50ModelRepository.saveAll(it);
	}

}
