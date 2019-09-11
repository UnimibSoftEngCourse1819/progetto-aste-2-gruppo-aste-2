package model.user;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * TODO check if there is a way to format a LocalDate 
 * @author Vallero
 *
 */
public class DateAttribute extends UserAttribute {
	private LocalDate date;
	public static SimpleDateFormat FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

	public DateAttribute(String name, boolean required, LocalDate date) {
		super(name, required);
		this.date = date;
	}

	
	@Override
	public Object getValue() {
		return date;
	}

}
