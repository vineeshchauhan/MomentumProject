package com.app.readfile;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.constants.Constants;
import com.app.model.EquitySymbolModel;
import com.app.model.HighLowPriceModel;
import com.app.model.PriceModel;
import com.app.service.impl.EquitySymbolService;
import com.app.service.impl.HighLowPriceModelService;
import com.app.service.impl.PriceModelService;
import com.app.util.Utility;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * Process downloaded file.
 */
@Component
public class ProcessBhavCopy {
	
	@Value("${spring.filePath}")
	String filePath;

	@Value("${spring.archieveFileLoc}")
	String archieveFileLoc;

	@Autowired
	EquitySymbolService equitySymbolService;
	
	@Autowired
	HighLowPriceModelService highLowPriceModelService;
	
	@Autowired
	PriceModelService priceModelService;
	
	private static Logger LOG = LoggerFactory.getLogger(ProcessBhavCopy.class);

	/**
	 * Read the csv file using CSVReader and save it to DB.
	 */
	public void readFileAndSave() {
		String fileLoc = Utility.getFileName(filePath, archieveFileLoc);
		LocalDate date = Utility.getTodaysDate(filePath, archieveFileLoc);
		LOG.info("Read File at " + fileLoc);
		List<List<String>> records = new ArrayList<List<String>>();
		try (CSVReader csvReader = new CSVReader(new FileReader(fileLoc));) {
			String[] values = null;
			try {
				while ((values = csvReader.readNext()) != null) {
					records.add(Arrays.asList(values));
				}
			} catch (CsvValidationException e) {
				LOG.error("Error in reading csv file : "+e.getMessage());

			}
		} catch (IOException e) {
			LOG.error("File Not found :" + e.getMessage());
		}

		Iterator<List<String>> it = records.iterator();
		while (it.hasNext()) {
			List<String> row = it.next();
			if (Constants.MKT.equals(row.get(0))) {
				continue;
			}
			if ("EQ".equals(row.get(Constants.SERIES_N))
					|| "BQ".equals(row.get(Constants.SERIES_N))
					   ||"BE".equals(row.get(Constants.SERIES_N))) {

				EquitySymbolModel equitySymbolModel = new EquitySymbolModel();
				equitySymbolModel.setEquitySymbol(row.get(Constants.SYMBOL_N) != null ? row.get(Constants.SYMBOL_N) : "");
				equitySymbolModel.setEquityName(row.get(Constants.SECURITY_N) != null ? row.get(Constants.SECURITY_N) : "");
				EquitySymbolModel equitySymbolModelSaved = equitySymbolService.saveOrUpdate(equitySymbolModel);

				HighLowPriceModel highLowPriceModel = new HighLowPriceModel();
				highLowPriceModel.setEquitySymbolId(equitySymbolModelSaved.getEquitySymbolId());
				highLowPriceModel.setWeek52High(Double.valueOf((row.get(Constants.HI_52_WK_N) != null && !row.get(Constants.HI_52_WK_N).isEmpty() && !row.get(Constants.HI_52_WK_N).isBlank()) ? row.get(Constants.HI_52_WK_N) : "0.0"));
				highLowPriceModel.setWeek52Low(Double.valueOf((row.get(Constants.LO_52_WK_N) != null && !row.get(Constants.LO_52_WK_N).isEmpty() && !row.get(Constants.LO_52_WK_N).isBlank()) ? row.get(Constants.LO_52_WK_N) : "0.0"));
				highLowPriceModel.setDate(date);
				highLowPriceModelService.saveOrUpdate(highLowPriceModel);

				PriceModel priceModel = new PriceModel();
				priceModel.setEquitySymbolId(equitySymbolModelSaved.getEquitySymbolId());
				priceModel.setDate(date);
				priceModel.setPreviousClosePrice(Double.valueOf((row.get(Constants.PREV_CL_PR_N) != null && !row.get(Constants.PREV_CL_PR_N).isEmpty() && !row.get(Constants.PREV_CL_PR_N).isBlank()) ? row.get(Constants.PREV_CL_PR_N) : "0.0"));
				priceModel.setOpenPrice(Double.valueOf((row.get(Constants.OPEN_PRICE_N) != null && !row.get(Constants.OPEN_PRICE_N).isEmpty() && !row.get(Constants.OPEN_PRICE_N).isBlank()) ? row.get(Constants.OPEN_PRICE_N) : "0.0"));
				priceModel.setHighPrice(Double.valueOf((row.get(Constants.HIGH_PRICE_N) != null && !row.get(Constants.HIGH_PRICE_N).isEmpty() && !row.get(Constants.HIGH_PRICE_N).isBlank()) ? row.get(Constants.HIGH_PRICE_N) : "0.0"));
				priceModel.setLowPrice(Double.valueOf((row.get(Constants.LOW_PRICE_N) != null && !row.get(Constants.LOW_PRICE_N).isEmpty() && !row.get(Constants.LOW_PRICE_N).isBlank()) ? row.get(Constants.LOW_PRICE_N) : "0.0"));
				priceModel.setClosePrice(Double.valueOf((row.get(Constants.CLOSE_PRICE_N) != null && !row.get(Constants.CLOSE_PRICE_N).isEmpty() && !row.get(Constants.CLOSE_PRICE_N).isBlank()) ? row.get(Constants.CLOSE_PRICE_N) : "0.0"));
				priceModel.setTradingValue(Double.valueOf((row.get(Constants.NET_TRDVAL_N) != null && !row.get(Constants.NET_TRDVAL_N).isEmpty() && !row.get(Constants.NET_TRDVAL_N).isBlank()) ? row.get(Constants.NET_TRDVAL_N) : "0.0"));
				priceModel.setTradingVolume(Double.valueOf((row.get(Constants.NET_TDDQTY_N) != null && !row.get(Constants.NET_TDDQTY_N).isEmpty() && !row.get(Constants.NET_TDDQTY_N).isBlank()) ? row.get(Constants.NET_TDDQTY_N) : "0.0"));
				priceModelService.saveOrUpdate(priceModel);
			}

		}
	}
}
