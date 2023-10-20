package com.app.readfile;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
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
import com.app.model.NiftyMidSmallCap400Model;
import com.app.service.impl.EquitySymbolService;
import com.app.service.impl.NiftyMidSmallCap400ModelService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Component
public class ProcessNiftyMidSmallCap400Index {

	@Value("${nifty_midsmallcap_400}")
	String fileName;

	@Autowired
	EquitySymbolService equitySymbolService;

	@Autowired
	NiftyMidSmallCap400ModelService niftyMidSmallCap400ModelService;

	private static Logger LOG = LoggerFactory.getLogger(ProcessBhavCopy.class);

	/**
	 * Read the csv file using CSVReader and save it to DB.
	 */
	public void readFileAndSave() {
		File dir = new File("D:\\documents\\Personal\\Momentum\\Index Data");

		File[] foundFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith(fileName);
			}

		});

		List<List<String>> records = new ArrayList<List<String>>();
		for (File file : foundFiles) {
			try (CSVReader csvReader = new CSVReader(new FileReader(file.getAbsolutePath()))) {
				String[] values = null;
				try {
					while ((values = csvReader.readNext()) != null) {
						records.add(Arrays.asList(values));
					}
				} catch (CsvValidationException e) {
					LOG.error("Error in reading csv file : " + e.getMessage());

				}
			} catch (IOException e) {
				LOG.error("File Not found :" + e.getMessage());
			}
			saveIndexData(records);
		}

	}

	private void saveIndexData(List<List<String>> records) {
		List<NiftyMidSmallCap400Model> list = new ArrayList<>();
		Iterator<List<String>> it = records.iterator();
		while (it.hasNext()) {
			List<String> row = it.next();
			if (Constants.OPEN.equals(row.get(1).strip()) || Constants.NIFTY_400.equals(row.get(0))) {
				continue;
			}
			Integer symbolId = equitySymbolService.findByEquitySymbol(row.get(0)).getEquitySymbolId();
			NiftyMidSmallCap400Model nifty50 = niftyMidSmallCap400ModelService.findByEquitySymbolId(symbolId);
			if (nifty50 == null) {
				nifty50 = new NiftyMidSmallCap400Model();
				nifty50.setEquitySymbolId(equitySymbolService.findByEquitySymbol(row.get(0)).getEquitySymbolId());
				list.add(nifty50);
			}

			if (!list.isEmpty()) {
				niftyMidSmallCap400ModelService.saveAll(list);
			}

		}

	}

}
