package com.app.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NIFTY_MIDSMALLCAP_400")
public class NiftyMidSmallCap400Model {

	@Id
	@Column(name = "NIFTY_MIDSMALLCAP_400_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private Integer niftyMidSmallCap400;

	@Column(name = "EQ_SYMBOL_ID")
	private Integer equitySymbolId;

	public Integer getNiftyMidSmallCap400() {
		return niftyMidSmallCap400;
	}

	public void setNiftyMidSmallCap400(Integer niftyMidSmallCap400) {
		this.niftyMidSmallCap400 = niftyMidSmallCap400;
	}

	public Integer getEquitySymbolId() {
		return equitySymbolId;
	}

	public void setEquitySymbolId(Integer equitySymbolId) {
		this.equitySymbolId = equitySymbolId;
	}

}
