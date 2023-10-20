package com.app.model;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "HIGH_LOW_PRICE")

public class HighLowPriceModel {

	@Id
	@Column(name = "HIGH_LOW_PRICE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	Integer highLowPriceId;

	@Column(name = "EQ_SYMBOL_ID")
	Integer equitySymbolId;

	@Column(name = "WEEK_52_HIGH")
	Double week52High;

	@Column(name = "WEEK_52_LOW")
	Double week52Low;
	
	@Column(name = "CUR_DATE")
	LocalDate date;

	public Integer getHighLowPriceId() {
		return highLowPriceId;
	}

	public void setHighLowPriceId(Integer highLowPriceId) {
		this.highLowPriceId = highLowPriceId;
	}

	public Integer getEquitySymbolId() {
		return equitySymbolId;
	}

	public void setEquitySymbolId(Integer equitySymbolId) {
		this.equitySymbolId = equitySymbolId;
	}

	public Double getWeek52High() {
		return week52High;
	}

	public void setWeek52High(Double week52High) {
		this.week52High = week52High;
	}

	public Double getWeek52Low() {
		return week52Low;
	}

	public void setWeek52Low(Double week52Low) {
		this.week52Low = week52Low;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, equitySymbolId, highLowPriceId, week52High, week52Low);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HighLowPriceModel other = (HighLowPriceModel) obj;
		return Objects.equals(date, other.date) && Objects.equals(equitySymbolId, other.equitySymbolId)
				&& Objects.equals(highLowPriceId, other.highLowPriceId) && Objects.equals(week52High, other.week52High)
				&& Objects.equals(week52Low, other.week52Low);
	}

	
}
