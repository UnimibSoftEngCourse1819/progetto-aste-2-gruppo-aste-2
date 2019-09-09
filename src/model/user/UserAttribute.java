package model.user;

public abstract class UserAttribute {
	protected String name;
	protected boolean required;
	
	protected UserAttribute(String name, boolean required) {
		this.name = name;
		this.required = required;
	}
	
	public String getName() {
		return name;
	}
	public boolean isRequired() {
		return required;
	}
	
	public abstract Object getValue();
	
}
