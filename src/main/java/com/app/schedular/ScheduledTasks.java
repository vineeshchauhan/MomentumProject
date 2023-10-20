package com.app.schedular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.preparedailyreport.PrepareDailyReport;
import com.app.preparedailyreport.PrepareNifty100DailyReport;
import com.app.preparedailyreport.PrepareNifty500DailyReport;
import com.app.preparedailyreport.PrepareNifty50DailyReport;
import com.app.preparedailyreport.PrepareNiftyMicroCap250DailyReport;
import com.app.preparedailyreport.PrepareNiftyMidCap100DailyReport;
import com.app.preparedailyreport.PrepareNiftyMidCap150DailyReport;
import com.app.preparedailyreport.PrepareNiftyMidCap50DailyReport;
import com.app.preparedailyreport.PrepareNiftyMidSmallCap400DailyReport;
import com.app.preparedailyreport.PrepareNiftyNext50DailyReport;
import com.app.preparedailyreport.PrepareNiftySmallCap100DailyReport;
import com.app.preparedailyreport.PrepareNiftySmallCap250DailyReport;
import com.app.preparedailyreport.PrepareNiftySmallCap50DailyReport;
import com.app.preparedailyreport.PrepareNiftyTotal750DailyReport;
import com.app.prepareweeklyreport.PrepareNifty100WeeklyReport;
import com.app.prepareweeklyreport.PrepareNifty500WeeklyReport;
import com.app.prepareweeklyreport.PrepareNifty50WeeklyReport;
import com.app.prepareweeklyreport.PrepareNiftyMicroCap250WeeklyReport;
import com.app.prepareweeklyreport.PrepareNiftyMidCap100WeeklyReport;
import com.app.prepareweeklyreport.PrepareNiftyMidCap150WeeklyReport;
import com.app.prepareweeklyreport.PrepareNiftyMidCap50WeeklyReport;
import com.app.prepareweeklyreport.PrepareNiftyMidSmallCap400WeeklyReport;
import com.app.prepareweeklyreport.PrepareNiftyNext50WeeklyReport;
import com.app.prepareweeklyreport.PrepareNiftySmallCap100WeeklyReport;
import com.app.prepareweeklyreport.PrepareNiftySmallCap250WeeklyReport;
import com.app.prepareweeklyreport.PrepareNiftySmallCap50WeeklyReport;
import com.app.prepareweeklyreport.PrepareNiftyTotal750WeeklyReport;
import com.app.prepareweeklyreport.PrepareWeeklyReport;
import com.app.readfile.DownloadBhavCopy;
import com.app.readfile.ProcessBhavCopy;
import com.app.readfile.ProcessNifty100Index;
import com.app.readfile.ProcessNifty500Index;
import com.app.readfile.ProcessNifty50Index;
import com.app.readfile.ProcessNiftyMicroCap250Index;
import com.app.readfile.ProcessNiftyMidCap100Index;
import com.app.readfile.ProcessNiftyMidCap150Index;
import com.app.readfile.ProcessNiftyMidCap50Index;
import com.app.readfile.ProcessNiftyMidSmallCap400Index;
import com.app.readfile.ProcessNiftyNext50Index;
import com.app.readfile.ProcessNiftySmallCap100Index;
import com.app.readfile.ProcessNiftySmallCap250Index;
import com.app.readfile.ProcessNiftySmallCap50Index;
import com.app.readfile.ProcessNiftyTotal750Index;

@Component
public class ScheduledTasks {

	private static Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	DownloadBhavCopy file;

	@Autowired
	ProcessBhavCopy processBhavCopy;

	@Autowired
	PrepareDailyReport prepareDailyReport;
	
	@Autowired
	PrepareNifty50DailyReport prepareNifty50DailyReport;
	
	@Autowired
	PrepareNifty100DailyReport prepareNifty100DailyReport;
	
	@Autowired
	PrepareNiftyNext50DailyReport prepareNiftyNext50DailyReport;
	
	@Autowired
	PrepareNifty500DailyReport prepareNifty500DailyReport;
	
	@Autowired
	PrepareNiftyTotal750DailyReport prepareNiftyTotal750DailyReport;
	
	@Autowired
	PrepareNiftyMidCap50DailyReport prepareNiftyMidCap50DailyReport;
	
	@Autowired
	PrepareNiftyMidCap100DailyReport prepareNiftyMidCap100DailyReport;
	
	@Autowired
	PrepareNiftyMidCap150DailyReport prepareNiftyMidCap150DailyReport;
	
	@Autowired
	PrepareNiftySmallCap50DailyReport prepareNiftySmallCap50DailyReport;
	
	@Autowired
	PrepareNiftySmallCap100DailyReport prepareNiftySmallCap100DailyReport;
	
	@Autowired
	PrepareNiftySmallCap250DailyReport prepareNiftySmallCap250DailyReport;
	
	@Autowired
	PrepareNiftyMicroCap250DailyReport prepareNiftyMicroCap250DailyReport;
	
	@Autowired
	PrepareNiftyMidSmallCap400DailyReport prepareNiftyMidSmallCap400DailyReport;
	
	@Autowired
	PrepareNifty50WeeklyReport prepareNifty50WeeklyReport;
	
	@Autowired
	PrepareNifty100WeeklyReport prepareNifty100WeeklyReport;
	
	@Autowired
	PrepareNiftyNext50WeeklyReport prepareNiftyNext50WeeklyReport;
	
	@Autowired
	PrepareNifty500WeeklyReport prepareNifty500WeeklyReport;
	
	@Autowired
	PrepareNiftyTotal750WeeklyReport prepareNiftyTotal750WeeklyReport;
	
	@Autowired
	PrepareNiftyMidCap50WeeklyReport prepareNiftyMidCap50WeeklyReport;
	
	@Autowired
	PrepareNiftyMidCap100WeeklyReport prepareNiftyMidCap100WeeklyReport;
	
	@Autowired
	PrepareNiftyMidCap150WeeklyReport prepareNiftyMidCap150WeeklyReport;
	
	@Autowired
	PrepareNiftySmallCap50WeeklyReport prepareNiftySmallCap50WeeklyReport;
	
	@Autowired
	PrepareNiftySmallCap100WeeklyReport prepareNiftySmallCap100WeeklyReport;
	
	@Autowired
	PrepareNiftySmallCap250WeeklyReport prepareNiftySmallCap250WeeklyReport;
	
	@Autowired
	PrepareNiftyMicroCap250WeeklyReport prepareNiftyMicroCap250WeeklyReport;
	
	@Autowired
	PrepareNiftyMidSmallCap400WeeklyReport prepareNiftyMidSmallCap400WeeklyReport;
	
	@Autowired
	PrepareWeeklyReport prepareWeeklyReport;
	@Autowired
	ProcessNifty50Index processNifty50Index;
	@Autowired
	ProcessNiftyNext50Index processNiftyNext50Index;
	@Autowired
	ProcessNifty100Index processNifty100Index;
	@Autowired
	ProcessNifty500Index processNifty500Index;
	@Autowired
	ProcessNiftyTotal750Index processNiftyTotal750Index;
	@Autowired
	ProcessNiftyMidCap50Index processNiftyMidCap50Index;
	@Autowired
	ProcessNiftyMidCap100Index processNiftyMidCap100Index;
	@Autowired
	ProcessNiftyMidCap150Index processNiftyMidCap150Index;
	@Autowired
	ProcessNiftySmallCap50Index processNiftySmallCap50Index;
	@Autowired
	ProcessNiftySmallCap100Index processNiftySmallCap100Index;
	@Autowired
	ProcessNiftySmallCap250Index processNiftySmallCap250Index;
	@Autowired
	ProcessNiftyMicroCap250Index processNiftyMicroCap250Index;
	@Autowired
	ProcessNiftyMidSmallCap400Index processNiftyMidSmallCap400Index;

	@Scheduled(cron = " 0 11 00 * * *") // seconds minutes hours days
	public void DailyTasks() {
		LOG.info("Download File");
		file.downloadFile();

		LOG.info("Process File");
		processBhavCopy.readFileAndSave();

		LOG.info("Prepare Daily Report");
		prepareDailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty 50 Daily Report");
		prepareNifty50DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty Next 50 Daily Report");
		prepareNiftyNext50DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty 100 Daily Report");
		prepareNifty100DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty 500 Daily Report");
		prepareNifty500DailyReport.createDailyReport();

		LOG.info("Prepare Nifty MidCap 50 Daily Report");
		prepareNiftyMidCap50DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty MidCap 100 Daily Report");
		prepareNiftyMidCap100DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty MidCap 150 Daily Report");
		prepareNiftyMidCap150DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty SmallCap 50 Daily Report");
		prepareNiftyMidCap150DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty SmallCap 100 Daily Report");
		prepareNiftySmallCap100DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty SmallCap 250 Daily Report");
		prepareNiftySmallCap250DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty Mid Small Cap 400 Daily Report");
		prepareNiftyMidSmallCap400DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty Total 750 Daily Report");
		prepareNiftyTotal750DailyReport.createDailyReport();
		
		LOG.info("Prepare Nifty Micro Cap 250 Daily Report");
		prepareNiftyMicroCap250DailyReport.createDailyReport();
	}

	@Scheduled(cron = " 0 03 17 * * FRI,SAT,SUN") // seconds minutes hours days
	public void weeklyTasks() {
		LOG.info("Prepare Weekly Report");
		prepareWeeklyReport.createWeeklyReport();
		
		LOG.info("Prepare Nifty 50 Weekly Report");
		prepareNifty50WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty Next 50 Weekly Report");
		prepareNifty100WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty 100 Weekly Report");
		prepareNiftyNext50WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty 500 Weekly Report");
		prepareNifty500WeeklyReport.createDailyReport();

		LOG.info("Prepare Nifty MidCap 50 Weekly Report");
		prepareNiftyTotal750WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty MidCap 100 Weekly Report");
		prepareNiftyMidCap50WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty MidCap 150 Weekly Report");
		prepareNiftyMidCap100WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty SmallCap 50 Weekly Report");
		prepareNiftyMidCap150WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty SmallCap 100 Weekly Report");
		prepareNiftySmallCap50WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty SmallCap 250 Weekly Report");
		prepareNiftySmallCap100WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty Mid Small Cap 400 Weekly Report");
		prepareNiftySmallCap250WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty Total 750 Weekly Report");
		prepareNiftyMicroCap250WeeklyReport.createDailyReport();
		
		LOG.info("Prepare Nifty Micro Cap 250 Weekly Report");
		prepareNiftyMidSmallCap400WeeklyReport.createDailyReport();
	}

	@Scheduled(cron = " * 11 23 * * *") // seconds minutes hours days
	public void saveIndexData() {
		LOG.info("Save Index Data");
		processNifty50Index.readFileAndSave();
		processNifty100Index.readFileAndSave();
		processNiftyNext50Index.readFileAndSave();
		processNiftyMidCap50Index.readFileAndSave();
		processNiftyMidCap100Index.readFileAndSave();
		processNiftyMidCap150Index.readFileAndSave();
		processNiftySmallCap50Index.readFileAndSave();
		processNiftySmallCap100Index.readFileAndSave();
		processNiftySmallCap250Index.readFileAndSave();
		processNiftyMicroCap250Index.readFileAndSave();
		processNiftyMidSmallCap400Index.readFileAndSave();
		processNiftyTotal750Index.readFileAndSave();
		processNifty500Index.readFileAndSave();
	}

}
