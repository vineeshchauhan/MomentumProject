package com.app.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NIFTY_100")
public class Nifty100Model {

	@Id
	@Column(name = "NIFTY_100_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private Integer nifty100Id;

	@Column(name = "EQ_SYMBOL_ID")
	private Integer equitySymbolId;

	public Integer getNifty100Id() {
		return nifty100Id;
	}

	public void setNifty100Id(Integer nifty100Id) {
		this.nifty100Id = nifty100Id;
	}

	public Integer getEquitySymbolId() {
		return equitySymbolId;
	}

	public void setEquitySymbolId(Integer equitySymbolId) {
		this.equitySymbolId = equitySymbolId;
	}

}
