package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.NiftyMidCap150Model;
import com.app.repository.NiftyMidCap150ModelRepository;

@Component
public class NiftyMidCap150ModelService {

	@Autowired
	NiftyMidCap150ModelRepository niftyMidCap150ModelRepository;

	public List<NiftyMidCap150Model> findAll() {

		return niftyMidCap150ModelRepository.findAll();
	}

	public NiftyMidCap150Model save(NiftyMidCap150Model model) {

		return niftyMidCap150ModelRepository.save(model);
	}
	
	public NiftyMidCap150Model findByEquitySymbolId(Integer equitySymbolId) {

		return niftyMidCap150ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public List<NiftyMidCap150Model> saveAll(List<NiftyMidCap150Model> it) {

		return niftyMidCap150ModelRepository.saveAll(it);
	}
}
