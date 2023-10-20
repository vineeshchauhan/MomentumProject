package com.app.preparedailyreport;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.model.Nifty50Model;
import com.app.model.PriceModel;
import com.app.modeloutput.DailyReportModel;
import com.app.service.impl.EquitySymbolService;
import com.app.service.impl.Nifty50ModelService;
import com.app.service.impl.PriceModelService;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Prepare daily reports
 */
@Component
public class PrepareNifty50DailyReport {

	@Value("${daily_loc_prefix}")
	private String DAILY_LOC_PREFIX;

	@Autowired
	PriceModelService priceModelService;
	
	@Autowired
	EquitySymbolService equitySymbolService;
	
	@Autowired
	Nifty50ModelService nifty50ModelService;

	private static Logger LOG = LoggerFactory.getLogger(PrepareNifty50DailyReport.class);

	/**
	 * The method is responsible for creating 2 reports, 1- stocks which rises more
	 * than 5 percent. 2- stocks which falls more than 5 percent.
	 */
	public void createDailyReport() {

		// fetch today's equity data
		List<PriceModel> list = priceModelService.findByDate(LocalDate.now());
		
		List<Nifty50Model> nifty50ModelList = nifty50ModelService.findAll();
		List<Integer> listOfEquitySymbolInNifty50 = nifty50ModelList.stream().map(model -> model.getEquitySymbolId()).collect(Collectors.toList());
		
		List<PriceModel> listOfPriceModelInNifty50 = list.stream().filter(model -> listOfEquitySymbolInNifty50.contains(model.getEquitySymbolId())).collect(Collectors.toList());
		// Get date
		String strDate = getDateFormat();
		
		CustomMappingStrategy<DailyReportModel> mappingStrategy = createMappingStrategy();
		// prepare a list of stocks rises more than 5%
		createFivePercentGainerReport(listOfPriceModelInNifty50, strDate, mappingStrategy);

		// prepare a list of stocks fall more than 5%
		createFivePercentLosserReport(listOfPriceModelInNifty50, strDate, mappingStrategy);
	}

	private void createFivePercentGainerReport(List<PriceModel> list, String strDate, CustomMappingStrategy<DailyReportModel> mappingStrategy) {
		List<PriceModel> fivePercentGainer = list.stream()
				.filter(model -> ((model.getClosePrice() - model.getPreviousClosePrice())
						/ model.getPreviousClosePrice()) > 0.05)
				.collect(Collectors.toList());

		// prepare file for greater than five percent
		File file = new File(DAILY_LOC_PREFIX + "Nifty50FivePercentGainer" + strDate + ".csv");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdir();
				file.createNewFile();
			} catch (IOException e) {
				LOG.error("Error in create file for Nifty50FivePercentGainer : " + e.getMessage());
			}
		}
		try (Writer writer = new PrintWriter(file)) {
			StatefulBeanToCsv<DailyReportModel> beanToCsv = new StatefulBeanToCsvBuilder<DailyReportModel>(writer)
					.withMappingStrategy(mappingStrategy).build();
			List<DailyReportModel> dailyReportModel = convertToDailyReportModel(fivePercentGainer);
			dailyReportModel.sort(new SortDailyFivePercentGainerReport());
			beanToCsv.write(dailyReportModel);
		} catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
			LOG.error("Exception occured in daily report processing for, Nifty50FivePercentGainer : "
					+ e.getMessage());
		}
		
	}
	
	private void createFivePercentLosserReport(List<PriceModel> list, String strDate, CustomMappingStrategy<DailyReportModel> mappingStrategy) {
		List<PriceModel> fivePercentLooser = list.stream()
				.filter(model -> ((model.getClosePrice() - model.getPreviousClosePrice())
						/ model.getPreviousClosePrice()) < -0.05)
				.collect(Collectors.toList());

		// prepare file for greater than five percent
		File file = new File(DAILY_LOC_PREFIX + "Nifty50FivePercentLooser" + strDate + ".csv");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdir();
				file.createNewFile();
			} catch (IOException e) {
				LOG.error("Error in create file for Nifty50FivePercentLooser : " + e.getMessage());
			}
		}
		try (Writer writer = new PrintWriter(file)) {
			StatefulBeanToCsv<DailyReportModel> beanToCsv = new StatefulBeanToCsvBuilder<DailyReportModel>(writer)
					.withMappingStrategy(mappingStrategy).build();
			List<DailyReportModel> dailyReportModel = convertToDailyReportModel(fivePercentLooser);
		    dailyReportModel.sort(new SortDailyFivePercentLosserReport());
			beanToCsv.write(dailyReportModel);
		} catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
			LOG.error("Exception occured in daily report processing for Nifty50FivePercentLooser : "
					+ e.getMessage());
		}
		
	}

	private CustomMappingStrategy<DailyReportModel> createMappingStrategy() {
		String[] fields = { "date", "equityName", "previousClosePrice", "closePrice", "percentageChange" };
		String[] columns = new String[] { "Date", "Symbol", "Previous Close Price", "Close Price",
				"Percentage Change" };
		CustomMappingStrategy<DailyReportModel> mappingStrategy = new CustomMappingStrategy<>(columns);
		mappingStrategy.setType(DailyReportModel.class);
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
	
	public List<DailyReportModel> convertToDailyReportModel(List<PriceModel> priceModelList) {

		List<DailyReportModel> listDailyReportModel = new ArrayList<>();
		for (PriceModel priceModel : priceModelList) {
			DailyReportModel dailyReportModel = new DailyReportModel();
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
	
	class SortDailyFivePercentGainerReport implements Comparator<DailyReportModel> {

		@Override
		public int compare(DailyReportModel dm1, DailyReportModel dm2) {
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
	
	class SortDailyFivePercentLosserReport implements Comparator<DailyReportModel> {

		@Override
		public int compare(DailyReportModel dm1, DailyReportModel dm2) {
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
	
}
