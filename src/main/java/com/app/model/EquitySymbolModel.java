package com.app.model;

import java.util.Objects;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EQUITY_SYMBOL")
public class EquitySymbolModel {

	@Id
	@Column(name = "EQ_SYMBOL_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	Integer equitySymbolId;
	
	@Column(name = "EQ_SYMBOL")
	String equitySymbol;
	
	@Column(name = "EQ_NAME")
	String equityName;
	
	public Integer getEquitySymbolId() {
		return equitySymbolId;
	}

	public void setEquitySymbolId(Integer equitySymbolId) {
		this.equitySymbolId = equitySymbolId;
	}

	public String getEquitySymbol() {
		return equitySymbol;
	}

	public void setEquitySymbol(String equitySymbol) {
		this.equitySymbol = equitySymbol;
	}

	public String getEquityName() {
		return equityName;
	}

	public void setEquityName(String equityName) {
		this.equityName = equityName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(equityName, equitySymbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquitySymbolModel other = (EquitySymbolModel) obj;
		return Objects.equals(equityName, other.equityName) && Objects.equals(equitySymbol, other.equitySymbol);
	}

}
