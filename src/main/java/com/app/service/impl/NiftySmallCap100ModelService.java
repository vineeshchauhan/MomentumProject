package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.NiftySmallCap100Model;
import com.app.repository.NiftySmallCap100ModelRepository;

@Component
public class NiftySmallCap100ModelService {

	@Autowired
	NiftySmallCap100ModelRepository niftySmallCap100ModelRepository;

	public List<NiftySmallCap100Model> findAll() {

		return niftySmallCap100ModelRepository.findAll();

	}

	public NiftySmallCap100Model save(NiftySmallCap100Model model) {

		return niftySmallCap100ModelRepository.save(model);
	}
	
	public NiftySmallCap100Model findByEquitySymbolId(Integer equitySymbolId) {

		return niftySmallCap100ModelRepository.findByEquitySymbolId(equitySymbolId);
	}
	
	public List<NiftySmallCap100Model> saveAll(List<NiftySmallCap100Model> it) {

		return niftySmallCap100ModelRepository.saveAll(it);
	}
}
