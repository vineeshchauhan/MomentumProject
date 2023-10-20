package com.app.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NIFTY_TOTAL_750")
public class NiftyTotal750Model {

	@Id
	@Column(name = "NIFTY_TOTAL_750_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private Integer niftyTotal750Id;

	@Column(name = "EQ_SYMBOL_ID")
	private Integer equitySymbolId;

	public Integer getNiftyTotal750Id() {
		return niftyTotal750Id;
	}

	public void setNiftyTotal750Id(Integer niftyTotal750Id) {
		this.niftyTotal750Id = niftyTotal750Id;
	}

	public Integer getEquitySymbolId() {
		return equitySymbolId;
	}

	public void setEquitySymbolId(Integer equitySymbolId) {
		this.equitySymbolId = equitySymbolId;
	}

}
