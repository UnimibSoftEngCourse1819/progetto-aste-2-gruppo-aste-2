package model.user;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Note : use java.util.Calendar.getInstance().getTime(); to get the current date
 * @author Vallero
 *
 */
public class DateAttribute extends UserAttribute {
	private Date date;
	public static SimpleDateFormat FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

	public DateAttribute(String name, boolean required, Date date) {
		super(name, required);
		this.date = date;
	}

	
	@Override
	public Object getValue() {
		return date;
	}

}
