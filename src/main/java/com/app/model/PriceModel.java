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
@Table(name = "DAILY_PRICE")
public class PriceModel {

	@Id
	@Column(name = "DAILY_PRICE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	Integer priceModelId;

	@Column(name = "EQ_SYMBOL_ID")
	Integer equitySymbolId;

	@Column(name = "CUR_DATE")
	LocalDate date;

	@Column(name = "PREVIOUS_CLOSE_PRICE")
	Double previousClosePrice;

	@Column(name = "OPEN_PRICE")
	Double openPrice;

	@Column(name = "HIGH_PRICE")
	Double highPrice;

	@Column(name = "LOW_PRICE")
	Double lowPrice;

	@Column(name = "CLOSE_PRICE")
	Double closePrice;

	@Column(name = "TRADED_VALUE")
	Double tradingValue;

	@Column(name = "TRADED_VOLUME")
	Double tradingVolume;

	public Integer getPriceModelId() {
		return priceModelId;
	}

	public Integer getEquitySymbolId() {
		return equitySymbolId;
	}

	public void setEquitySymbolId(Integer equitySymbolId) {
		this.equitySymbolId = equitySymbolId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getPreviousClosePrice() {
		return previousClosePrice;
	}

	public void setPreviousClosePrice(Double previousClosePrice) {
		this.previousClosePrice = previousClosePrice;
	}

	public Double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}

	public Double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	public Double getTradingValue() {
		return tradingValue;
	}

	public void setTradingValue(Double tradingValue) {
		this.tradingValue = tradingValue;
	}

	public Double getTradingVolume() {
		return tradingVolume;
	}

	public void setTradingVolume(Double tradingVolume) {
		this.tradingVolume = tradingVolume;
	}

	public void setPriceModelId(Integer priceModelId) {
		this.priceModelId = priceModelId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(closePrice, date, equitySymbolId, highPrice, lowPrice, openPrice, previousClosePrice,
				tradingValue, tradingVolume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceModel other = (PriceModel) obj;
		return Objects.equals(closePrice, other.closePrice) && Objects.equals(date, other.date)
				&& Objects.equals(equitySymbolId, other.equitySymbolId) && Objects.equals(highPrice, other.highPrice)
				&& Objects.equals(lowPrice, other.lowPrice) && Objects.equals(openPrice, other.openPrice)
				&& Objects.equals(previousClosePrice, other.previousClosePrice)
				&& Objects.equals(tradingValue, other.tradingValue)
				&& Objects.equals(tradingVolume, other.tradingVolume);
	}

}
