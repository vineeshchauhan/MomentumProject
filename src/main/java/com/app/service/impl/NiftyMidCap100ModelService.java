package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.NiftyMidCap100Model;
import com.app.repository.NiftyMidCap100ModelRepository;

@Component
public class NiftyMidCap100ModelService {

	@Autowired
	NiftyMidCap100ModelRepository niftyMidCap100ModelRepository;

	public List<NiftyMidCap100Model> findAll() {

		return niftyMidCap100ModelRepository.findAll();
	}
	
	public NiftyMidCap100Model save(NiftyMidCap100Model model) {

		return niftyMidCap100ModelRepository.save(model);
	}
	
	public NiftyMidCap100Model findByEquitySymbolId(Integer equitySymbolId) {

		return niftyMidCap100ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public List<NiftyMidCap100Model> saveAll(List<NiftyMidCap100Model> it) {

		return niftyMidCap100ModelRepository.saveAll(it);
	}
}

