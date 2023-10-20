package com.app.modeloutput;

import java.time.LocalDate;

public class DailyReportModel {
	
	LocalDate date;
	
	String equityName;

	Double previousClosePrice;

	Double closePrice;
	
	String percentageChange;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getEquityName() {
		return equityName;
	}

	public void setEquityName(String equityName) {
		this.equityName = equityName;
	}

	public Double getPreviousClosePrice() {
		return previousClosePrice;
	}

	public void setPreviousClosePrice(Double previousClosePrice) {
		this.previousClosePrice = previousClosePrice;
	}

	public Double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	public String getPercentageChange() {
		return percentageChange;
	}

	public void setPercentageChange(String percentageChange) {
		this.percentageChange = percentageChange;
	}

	@Override
	public String toString() {
		return "DailyReportModel [percentageChange=" + percentageChange + "]";
	}
	
}