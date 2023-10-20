package com.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.app.model.PriceModel;
import com.app.modeloutput.DailyReportModel;

public class Utility {
	
	/**
	 * covert from dd-MM-yy to ddmmyy
	 * @return
	 */
	public static String getTodaysDateFormatForToday() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YY");
		String strDate = formatter.format(date).replace("-", "");
		return strDate;
	}
	
	/**
	 * covert from dd-MM-yy to ddmmyy
	 * @return
	 */
	public static String getDateFormatForLocaleDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YY");
		String strDate = formatter.format(date).replace("-", "");
		return strDate;
	}

	/**
	 * return LocalDate created from file name.
	 * @param filePath
	 * @param fileLocation
	 * @return
	 */
	public static LocalDate getTodaysDate(String filePath, String fileLocation) {
		LocalDate date = null;
		if (fileLocation == null || fileLocation.isEmpty()) {
			date = LocalDate.now();
		} else {
			String dateString = fileLocation.substring(fileLocation.indexOf("Pd") + 2, fileLocation.indexOf(".csv"));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
			date = LocalDate.parse(dateString, formatter);
		}
		return date;
	}
	
	/**
	 * Explode the downloaded zip file and return file name to process.
	 * 
	 * @param filePath
	 * @param archieveFileLoc
	 * @return 
	 */
	public static String getFileName(String filePath, String archieveFileLoc) {
		if (archieveFileLoc == null || archieveFileLoc.isEmpty()) {
			String strDate = Utility.getTodaysDateFormatForToday();
			String zipFileName = filePath.concat("PR" + strDate + ".zip");
			String destFileName = filePath.concat("PR" + strDate);
			explodeZipFile(zipFileName, destFileName);
			return filePath.concat("PR" + strDate + "//Pd" + strDate + ".csv");
		} else {
			LocalDate date = Utility.getTodaysDate(null, archieveFileLoc);
			String strDate = Utility.getDateFormatForLocaleDate(date);
			String zipFileName = filePath.concat("PR" + strDate + ".zip");
			String destFileName = filePath.concat("PR" + strDate);
			explodeZipFile(zipFileName, destFileName);
			return filePath.concat("PR" + strDate + "//Pd" + strDate + ".csv");
		}
	}
	
	/**
	 * The zip file downloads from nse. The zip file should be exploded 
	 * to get all the files.
	 * @param zipFileName name of the zip file downloaded.
	 * @param destFileName destination folder to unzip.
	 */
	private static void explodeZipFile(String zipFileName, String destFileName) {
		
		//create destination directory if not existing
		File dir = new File(destFileName);
		if(!dir.exists()) dir.mkdirs();
		
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(zipFileName); ZipInputStream zis = new ZipInputStream(fis)){
            
            ZipEntry ze = zis.getNextEntry();
            // Process each file in zip
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destFileName + File.separator + fileName);
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}

}
