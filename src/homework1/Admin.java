package homework1;

import Utils.Validation;

public class Admin {
	protected String name;
	protected String username;
	protected String password;
	
	public Admin(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	public String getName() { return name; }
	public String getUsername() { return username;}
	
	
	public void setName(String name) {
		boolean valid = Validation.validate(name, "invalid name");
		if (valid) this.name = name;
	}

	public void setUsername(String username) {
		boolean valid = Validation.validate(username, "invalid name");
		if (valid) this.username = username;
	}

	public void setPassword(String password) {
		boolean valid = Validation.validate(password, "invalid name");
		if (valid) this.password = password;
	}



	public boolean login(String username, String password) {
		return username.equals(this.username) && password.equals(this.password);
	}
	
	public void menu(DeliverySystem DS) {
		// submenu of admin options	
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Admin) {
			Admin other = (Admin) obj;
			return other.username.equals(this.username);
		}
		return false;
	}
}
