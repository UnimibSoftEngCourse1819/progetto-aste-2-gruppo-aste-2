package model.user;

import exception.InvalidValueException;

public class PhoneNumber extends UserAttribute{
	private String number;

	protected PhoneNumber(String name, boolean required, String number) throws InvalidValueException {
		super(name, required);
		if(isValidNumber(number)) {
			this.number = number;
		} else {
			throw new InvalidValueException();
		}
	}
	
	public static boolean isValidNumber(String number) {
		number = "" + number.replaceAll("\\s+","");
		if(number.startsWith("+")) {
			number = number.substring(1);
		}
		return number.matches("[0-9]+");
	}

	@Override
	public Object getValue() {
		return number;
	}
}
