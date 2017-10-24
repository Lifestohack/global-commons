package org.commons.selenium;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.commons.constants.DIConstants;
import org.commons.properties.DIProperties;

public class DISetUp {

	public static void setUpLogDirectory() {
		String logDir = "";
		if (System.getProperty(DIConstants.LOG_SAVE_PATH) != null) {
			logDir = System.getProperty(DIConstants.LOG_SAVE_PATH);
		} else {
			logDir = DIProperties.getInstance().getTechnicalProperty(DIConstants.LOG_SAVE_PATH);
		}
		String logPath = logDir + getCurrentTimeStamp();
		String logFile = logPath + DIConstants.LOG_FILENAME;
		System.setProperty(DIConstants.LOG_SAVE_PATH, logFile);
		DIProperties.getInstance().setTechnicalProperty(DIConstants.LOG_SAVE_PATH, logPath);
		LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
		logContext.reconfigure();
	}

	private static String getCurrentTimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
	}
	
}
