package model.user;

/**
 * This is for simple String values
 * (name, surname, address, password)
 * @author Vallero
 *
 */
public class StringAttribute extends UserAttribute {

	private String value;
	private static int MINIM_LENGTH_PASSWORD = 8;
	
	public StringAttribute(String name, boolean required, String value) {
		super(name, required);
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}
	
	/**
	 * Note : we didn't use Regular Expressions
	 * for Sonarqube :"Using regular expressions is security-sensitive"
	 */
	public static boolean isValidPassword(String password) {
		boolean hasDigit = false;
		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		boolean hasSpecialCharacther = false;
		
		if(password.length() >= MINIM_LENGTH_PASSWORD) {
			for(int index = 0; index < password.length(); index++) {
				Character ch = password.charAt(index);
		        if( Character.isDigit(ch)) {
		        	hasDigit = true;
		        } else if (Character.isUpperCase(ch)) {
		        	hasUpperCase = true;
		        } else if (Character.isLowerCase(ch)) {
		        	hasLowerCase = true;
		        } else {
		        	hasSpecialCharacther = true;
		        }
			}
		}
		return hasDigit && hasUpperCase && hasLowerCase && hasSpecialCharacther;
	}

}
