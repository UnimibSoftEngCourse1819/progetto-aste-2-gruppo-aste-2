package exception;

public class InvalidParameterException extends Exception {
	private static final long serialVersionUID = -4404887727110888332L;	
	
	public InvalidParameterException(String string) {
		super(string);
	}
}
