package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.NiftySmallCap250Model;
import com.app.repository.NiftySmallCap250ModelRepository;

@Component
public class NiftySmallCap250ModelService {

	@Autowired
	NiftySmallCap250ModelRepository niftySmallCap250ModelRepository;

	public List<NiftySmallCap250Model> findAll() {

		return niftySmallCap250ModelRepository.findAll();

	}
	
	public NiftySmallCap250Model save(NiftySmallCap250Model model) {

		return niftySmallCap250ModelRepository.save(model);
	}
	
	public NiftySmallCap250Model findByEquitySymbolId(Integer equitySymbolId) {

		return niftySmallCap250ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public List<NiftySmallCap250Model> saveAll(List<NiftySmallCap250Model> it) {

		return niftySmallCap250ModelRepository.saveAll(it);
	}

}
