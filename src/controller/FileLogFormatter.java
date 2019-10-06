package controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * A support class for AnomalyLogger
 *
 */

public class FileLogFormatter extends Formatter {

	private static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";

	@Override
	public String format(LogRecord record) {
		if (record.getLevel() != Level.INFO && record.getThrown() != null) {
			return millisecondToStringDate(record.getMillis()) + "  " + record.getLevel() + "  " + record.getMessage() + " " + getStackTrace(record.getThrown()) + "\n";
		}
		return millisecondToStringDate(record.getMillis()) + "  " + record.getLevel() + "  " + record.getMessage() + "\n";
	}

	private String millisecondToStringDate(long millisecond) {
		SimpleDateFormat gregorianFormat = new SimpleDateFormat(FORMAT_DATE);
		return gregorianFormat.format(new Date(millisecond));
	}

	private String getStackTrace(Throwable e) {
		StringWriter error = new StringWriter();
		e.printStackTrace(new PrintWriter(error));
		return error.toString();
	}

}
