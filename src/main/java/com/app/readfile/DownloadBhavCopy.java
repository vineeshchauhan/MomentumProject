package com.app.readfile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.util.Utility;

/**
 * Download Bhav Copy of nse from nse website.
 */
@Component
public class DownloadBhavCopy {
	
	@Value("${url_prefix}")
	private String URL_PREFIX;
	
	@Value("${file_postfix}")
	private String FILE_POSTFIX;
	
	@Value("${loc_prefix}")
	private String LOC_PREFIX;
	
	@Value("${spring.archieveFileLoc}")
	String archieveFileLoc;
	
	private static Logger LOG = LoggerFactory.getLogger(DownloadBhavCopy.class);
	
	/**
	 * Download the nse bhav copy file fron nse website.
	 */
	public void downloadFile() {
		//get file date from archieveFileLoc
		String strDate = null;
		if(archieveFileLoc != null) {
			LocalDate date = Utility.getTodaysDate(null, archieveFileLoc);
			strDate = Utility.getDateFormatForLocaleDate(date);
		}else {
		    strDate = Utility.getTodaysDateFormatForToday();
		}
		// Prepare url for file download
		String url = URL_PREFIX + strDate + FILE_POSTFIX;
		LOG.info("Download file from : "+url);
		// Prepare path to download file
		String downloadDir = LOC_PREFIX + strDate + FILE_POSTFIX;
		LOG.info("Download file to : "+downloadDir);
		downloadUsingStream(url, downloadDir);
	}

	/**
	 * Download file using stream.
	 * @param url url to download file.
	 * @param downloadDir location to download file.
	 */
	public static void downloadUsingStream(String urlStr, String downloadDir) {
		trustEveryOne();
		
		URL url = null;
		URLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = url.openConnection();
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestProperty("Connection", "close");
			connection.connect();
		} catch (IOException e) {
			LOG.error("Exception in URL : "+e.getMessage());
		}
		try (BufferedInputStream bis = new BufferedInputStream(connection.getInputStream()); 
				FileOutputStream fis = new FileOutputStream(downloadDir)) {
		byte[] buffer = new byte[1024];
		int count = 0;
		while ((count = bis.read(buffer, 0, 1024)) != -1) {
			fis.write(buffer, 0, count);
		} 
		}catch (IOException e) {
			LOG.error("Exception in downloading file : "+e.getMessage());
		}
	}

	/**
	 * Overrides methods from HttpsURLConnection and X509TrustManager so that
	 * the method trusts all certificates.
	 */
	private static void trustEveryOne() {
		LOG.info("Trust Everyone ");
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
		SSLContext context = null;
		;
		try {
			context = SSLContext.getInstance("TLS");
			context.init(null, new X509TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
			} }, new SecureRandom());
		} catch (KeyManagementException e) {
			LOG.error("KeyManagementException : "+e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("NoSuchAlgorithmException : "+e.getMessage());
		}
		
		HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
	}
}
