package com.app;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.app.modeloutput.DailyReportModel;
import com.app.readfile.DownloadBhavCopy;

public class Aaa {

	static DownloadBhavCopy downloadBhavCopy;

	public static void main(String[] args) throws IOException {

		new Aaa().test();
		System.out.println(LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)));
		
	}

	private void test() {
		
		DailyReportModel d1 = new DailyReportModel();
		d1.setPercentageChange("17.00%");
		DailyReportModel d2 = new DailyReportModel();
		d2.setPercentageChange("7.00%");
		DailyReportModel d3 = new DailyReportModel();
		d3.setPercentageChange("15.00%");
		DailyReportModel d4 = new DailyReportModel();
		d4.setPercentageChange("11.00%");
		DailyReportModel d5 = new DailyReportModel();
		d5.setPercentageChange("4.00%");
		DailyReportModel d6 = new DailyReportModel();
		d6.setPercentageChange("19.93%");
		DailyReportModel d7 = new DailyReportModel();
		d7.setPercentageChange("17.02%");
		
		List<DailyReportModel> list = new ArrayList<>();

		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
		list.add(d5);
		list.add(d6);
		list.add(d7);

		list.sort(new SortDailyReportForMoreThanFivePerReport());
		System.out.println(list.toString());

	}

}

class SortDailyReportForMoreThanFivePerReport implements Comparator<DailyReportModel> {

	@Override
	public int compare(DailyReportModel dm1, DailyReportModel dm2) {
		Double d1 = Double.valueOf(dm1.getPercentageChange().substring(0, dm1.getPercentageChange().length() - 1));
		Double d2 = Double.valueOf(dm2.getPercentageChange().substring(0, dm2.getPercentageChange().length() - 1));
		if (d2 > d1) {
			return 1;
		} else if (d2 < d1) {
			return -1;
		} else {
			return 0;
		}
	}
}
