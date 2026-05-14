package homework1;

import Utils.UserInput;
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
		return this.username.equals(username) && this.password.equals(password);
	}
	
	public void menu(DeliverySystem DS) {
		while(true) {
			System.out.println("1. add customer");
			System.out.println("2. add restaurant admin");
			System.out.println("3. assign restaurant admin to restaurant");
			System.out.println("4. add restaurant");
			System.out.println("5. add rider");
			System.out.println("6. assign rider to order");
			int adminOption = UserInput.getInt("option");
			if(adminOption == 7) break;
			// validate option
		}
		// inner loop for admin options
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
