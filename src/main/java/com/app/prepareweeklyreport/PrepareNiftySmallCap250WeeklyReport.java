package com.app.prepareweeklyreport;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.model.NiftySmallCap250Model;
import com.app.model.PriceModel;
import com.app.modeloutput.WeeklyReportModel;
import com.app.service.impl.EquitySymbolService;
import com.app.service.impl.NiftySmallCap250ModelService;
import com.app.service.impl.PriceModelService;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Prepare weekly reports
 */
@Component
public class PrepareNiftySmallCap250WeeklyReport {

	@Value("${weekly_loc_prefix}")
	private String WEEKLY_LOC_PREFIX;

	@Autowired
	PriceModelService priceModelService;
	
	@Autowired
	EquitySymbolService equitySymbolService;
	
	@Autowired
	NiftySmallCap250ModelService niftySmallCap250ModelService;

	private static Logger LOG = LoggerFactory.getLogger(PrepareNiftySmallCap250WeeklyReport.class);

	/**
	 * The method is responsible for creating 2 reports, 1- stocks which rises more
	 * than 5 percent. 2- stocks which falls more than 5 percent.
	 */
	public void createDailyReport() {

		// fetch today's(Friday) equity data
		DayOfWeek friday = DayOfWeek.FRIDAY;
		LocalDate currentDay = LocalDate.now();
		if(currentDay.getDayOfWeek().compareTo(friday) != 0) {
			currentDay = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
		}
		List<PriceModel> listCW = priceModelService.findByDate(currentDay);

		// fetch today's(Friday) equity data
		List<PriceModel> listPW = priceModelService.findByDate(currentDay.minus(Period.ofDays(7)));
		
		List<NiftySmallCap250Model> niftyMidSmallCap400ModelList = niftySmallCap250ModelService.findAll();
		List<Integer> listOfEquitySymbolInMidSmallCap400 = niftyMidSmallCap400ModelList.stream().map(model -> model.getEquitySymbolId()).collect(Collectors.toList());
		
		List<PriceModel> listOfEquitySymbolInMidSmallCap400CW = listCW.stream().filter(model -> listOfEquitySymbolInMidSmallCap400.contains(model.getEquitySymbolId())).collect(Collectors.toList());
		List<PriceModel> listOfEquitySymbolInMidSmallCap400PW = listPW.stream().filter(model -> listOfEquitySymbolInMidSmallCap400.contains(model.getEquitySymbolId())).collect(Collectors.toList());
		// Get date
		String strDate = getDateFormat();
		
		CustomMappingStrategy<WeeklyReportModel> mappingStrategy = createMappingStrategy();
		// prepare a list of stocks rises more than 5%
		createFivePercentGainerReport(listOfEquitySymbolInMidSmallCap400PW,listOfEquitySymbolInMidSmallCap400CW, strDate, mappingStrategy);

		// prepare a list of stocks fall more than 5%
		createFivePercentLosserReport(listOfEquitySymbolInMidSmallCap400PW,listOfEquitySymbolInMidSmallCap400CW, strDate, mappingStrategy);
	}

	private void createFivePercentGainerReport(List<PriceModel> listPW, List<PriceModel> listCW, String strDate,
			CustomMappingStrategy<WeeklyReportModel> mappingStrategy) {
		List<WeeklyReportModel> weeklyReportList = new ArrayList<>();

		for (PriceModel model : listPW) {
			Double previousWeekClosingPrice = model.getClosePrice();
			Double currentWeekClosingPrice = 0.0;
			Optional<PriceModel> optional = listCW.stream()
					.filter(m -> m.getEquitySymbolId().equals(model.getEquitySymbolId())).findFirst();
			if(optional.isPresent()) {
				currentWeekClosingPrice = optional.get().getClosePrice();
			}
			Double perChange = ((currentWeekClosingPrice - previousWeekClosingPrice) / previousWeekClosingPrice) * 100.0;
			if (perChange > 5.0) {
				String percentageChange = new DecimalFormat("0.00").format(perChange) + "%";

				WeeklyReportModel weeklyReport = new WeeklyReportModel();
				weeklyReport.setDate(LocalDate.now());
				weeklyReport.setPreviousClosePrice(previousWeekClosingPrice);
				weeklyReport.setClosePrice(currentWeekClosingPrice);
				weeklyReport.setPercentageChange(percentageChange);
				weeklyReport.setEquityName(
						equitySymbolService.findByEquitySymbolId(model.getEquitySymbolId()).getEquityName());
				weeklyReportList.add(weeklyReport);
			}
		}
		weeklyReportList.sort(new SortDailyReportForMoreThanFivePerReport());
		// prepare file for greater than five percent
		File file = new File(WEEKLY_LOC_PREFIX + "NiftySmallCap250FivePercentGainer" + strDate + ".csv");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdir();
				file.createNewFile();
			} catch (IOException e) {
				LOG.error("Error in create file for NiftySmallCap250FivePercentGainer : " + e.getMessage());
			}
		}

		try (Writer writer = new PrintWriter(file)) {
			StatefulBeanToCsv<WeeklyReportModel> beanToCsv = new StatefulBeanToCsvBuilder<WeeklyReportModel>(writer)
					.withMappingStrategy(mappingStrategy).build();
			beanToCsv.write(weeklyReportList);
		} catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
			LOG.error("Exception occured in daily report processing for NiftySmallCap250FivePercentGainer : "
					+ e.getMessage());
		}

	}
	
	private void createFivePercentLosserReport(List<PriceModel> listPW, List<PriceModel> listCW, String strDate,
			CustomMappingStrategy<WeeklyReportModel> mappingStrategy) {

		List<WeeklyReportModel> weeklyReportList = new ArrayList<>();

		for (PriceModel model : listPW) {
			Double previousWeekClosingPrice = model.getClosePrice();
			Double currentWeekClosingPrice = 0.0;
			Optional<PriceModel> optional = listCW.stream()
					.filter(m -> m.getEquitySymbolId().equals(model.getEquitySymbolId())).findFirst();
			if(optional.isPresent()) {
				currentWeekClosingPrice = optional.get().getClosePrice();
			}
			Double perChange = ((currentWeekClosingPrice - previousWeekClosingPrice) / previousWeekClosingPrice) * 100.0;
			if (perChange < -5.0) {
				String percentageChange = new DecimalFormat("0.00").format(perChange) + "%";
				WeeklyReportModel weeklyReport = new WeeklyReportModel();
				weeklyReport.setDate(LocalDate.now());
				weeklyReport.setPreviousClosePrice(previousWeekClosingPrice);
				weeklyReport.setClosePrice(currentWeekClosingPrice);
				weeklyReport.setPercentageChange(percentageChange);
				weeklyReport.setEquityName(
						equitySymbolService.findByEquitySymbolId(model.getEquitySymbolId()).getEquityName());
				weeklyReportList.add(weeklyReport);
			}
		}
		weeklyReportList.sort(new SortDailyReportForLessThanFivePerReport());
		// prepare file for greater than five percent
		File file = new File(WEEKLY_LOC_PREFIX + "NiftySmallCap250FivePercentLosser" + strDate + ".csv");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdir();
				file.createNewFile();
			} catch (IOException e) {
				LOG.error("Error in create file for NiftySmallCap250FivePercentLosser : " + e.getMessage());
			}
		}

		try (Writer writer = new PrintWriter(file)) {
			StatefulBeanToCsv<WeeklyReportModel> beanToCsv = new StatefulBeanToCsvBuilder<WeeklyReportModel>(writer)
					.withMappingStrategy(mappingStrategy).build();
			beanToCsv.write(weeklyReportList);
		} catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
			LOG.error("Exception occured in daily report processing for NiftySmallCap250FivePercentLosser : "
					+ e.getMessage());
		}

	}

	private CustomMappingStrategy<WeeklyReportModel> createMappingStrategy() {
		String[] fields = { "date", "equityName", "previousClosePrice", "closePrice", "percentageChange" };
		String[] columns = new String[] { "Date", "Symbol", "Previous Close Price", "Close Price",
				"Percentage Change" };
		CustomMappingStrategy<WeeklyReportModel> mappingStrategy = new CustomMappingStrategy<>(columns);
		mappingStrategy.setType(WeeklyReportModel.class);
		mappingStrategy.setColumnMapping(fields);
		return mappingStrategy;
	}

	/**
	 * prepare date format for file name.
	 * 
	 * @return date format for file name
	 */
	private String getDateFormat() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YY");
		String strDate = formatter.format(date).replace("-", "");
		return strDate;
	}

	private static class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {

		String[] header;

		public CustomMappingStrategy(String[] cols) {
			header = cols;
		}

		@Override
		public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
			super.generateHeader(bean);
			return header;
		}
	}
	
	public List<WeeklyReportModel> convertToDailyReportModel(List<PriceModel> priceModelList) {

		List<WeeklyReportModel> listDailyReportModel = new ArrayList<>();
		for (PriceModel priceModel : priceModelList) {
			WeeklyReportModel dailyReportModel = new WeeklyReportModel();
			dailyReportModel.setDate(priceModel.getDate());
			dailyReportModel.setEquityName(equitySymbolService
					.findByEquitySymbolId(priceModel.getEquitySymbolId()).getEquityName());
			dailyReportModel.setPreviousClosePrice(priceModel.getPreviousClosePrice());
			dailyReportModel.setClosePrice(priceModel.getClosePrice());
			Double perChange = ((priceModel.getClosePrice() - priceModel.getPreviousClosePrice())
					/ priceModel.getPreviousClosePrice())*100.0;
			dailyReportModel.setPercentageChange(new DecimalFormat("0.00").format(perChange)+"%");
			listDailyReportModel.add(dailyReportModel);
		}
		return listDailyReportModel;
	}
	
	class SortDailyReportForLessThanFivePerReport implements Comparator<WeeklyReportModel> {

		@Override
		public int compare(WeeklyReportModel dm1, WeeklyReportModel dm2) {
			Double d1 = Double.valueOf(dm1.getPercentageChange().substring(0, dm1.getPercentageChange().length()-1));
			Double d2 = Double.valueOf(dm2.getPercentageChange().substring(0, dm2.getPercentageChange().length()-1));
			if(d1 > d2) {
				return 1;
			}else if(d1 < d2) {
				return -1;
			}else {
				return 0;
			}
		}
	}
	
	class SortDailyReportForMoreThanFivePerReport implements Comparator<WeeklyReportModel> {

		@Override
		public int compare(WeeklyReportModel dm1, WeeklyReportModel dm2) {
			Double d1 = Double.valueOf(dm1.getPercentageChange().substring(0, dm1.getPercentageChange().length()-1));
			Double d2 = Double.valueOf(dm2.getPercentageChange().substring(0, dm2.getPercentageChange().length()-1));
			if(d2 > d1) {
				return 1;
			}else if(d2 < d1) {
				return -1;
			}else {
				return 0;
			}
		}
	}
	
}
