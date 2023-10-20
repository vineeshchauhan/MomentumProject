package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.NiftyMicroCap250Model;
import com.app.repository.NiftyMicroCap250ModelRepository;

@Component
public class NiftyMicroCap250ModelService {

	@Autowired
	NiftyMicroCap250ModelRepository niftyMicroCap250ModelRepository;

	public List<NiftyMicroCap250Model> findAll() {

		return niftyMicroCap250ModelRepository.findAll();
	}
	
	public NiftyMicroCap250Model save(NiftyMicroCap250Model model) {

		return niftyMicroCap250ModelRepository.save(model);
	}
	
	public NiftyMicroCap250Model findByEquitySymbolId(Integer equitySymbolId) {

		return niftyMicroCap250ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public List<NiftyMicroCap250Model> saveAll(List<NiftyMicroCap250Model> it) {

		return niftyMicroCap250ModelRepository.saveAll(it);
	}
}
