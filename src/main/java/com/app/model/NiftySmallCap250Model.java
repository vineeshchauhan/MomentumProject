package com.app.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NIFTY_SMALLCAP_250")
public class NiftySmallCap250Model {
	
	@Id
	@Column(name = "NIFTY_SMALLCAP_250_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private Integer niftySmallCap250Id;

	@Column(name = "EQ_SYMBOL_ID")
	private Integer equitySymbolId;

	public Integer getNiftySmallCap250Id() {
		return niftySmallCap250Id;
	}

	public void setNiftySmallCap250Id(Integer niftySmallCap250Id) {
		this.niftySmallCap250Id = niftySmallCap250Id;
	}

	public Integer getEquitySymbolId() {
		return equitySymbolId;
	}

	public void setEquitySymbolId(Integer equitySymbolId) {
		this.equitySymbolId = equitySymbolId;
	}

}
