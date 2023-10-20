package com.app.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NIFTY_50")
public class Nifty50Model {

	@Id
	@Column(name = "NIFTY_50_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private Integer nifty50Id;

	@Column(name = "EQ_SYMBOL_ID")
	private Integer equitySymbolId;

	public Integer getNifty50Id() {
		return nifty50Id;
	}

	public void setNifty50Id(Integer nifty50Id) {
		this.nifty50Id = nifty50Id;
	}

	public Integer getEquitySymbolId() {
		return equitySymbolId;
	}

	public void setEquitySymbolId(Integer equitySymbolId) {
		this.equitySymbolId = equitySymbolId;
	}

}
