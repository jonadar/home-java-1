package homework1;

import java.security.Provider.Service;

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
	public String getUsername() { return username; }
	
	
	public void setName(String name) {
		boolean valid = Validation.isName(name);
		if (valid) this.name = name;
		else {
			System.out.println("invalid name");
		}
	}

	public void setUsername(String username) {
		if (Validation.isUsername(username)) this.username = username;
		else System.out.println("invalid username");
	}

	public void setPassword(String password) {
		if (Validation.isPassword(password)) this.password = password;
		else System.out.println("invalid password");
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
			System.out.println("7. logout");
			
			int option = UserInput.getIntFromRange(1, 7, "option");
			if(option == 7) break;
			
			switch (option) {
				case 1:
					Services.addCustomer(UserInput.s, DS.getCustomers()); // doesnt actually add yet, just creates
					break;
				case 2:
					Services.addRestAdmin(UserInput.s, DS.getRestaurantAdmins()); // doesnt actually add yet, just creates
					break;
				case 3:
					Services.assignRestAdminToRestaurant(DS.getRestaurantAdmins(), DS.getRestaurants());
					break;
				case 4:
					Services.addRestaurant(UserInput.s); // need to update code slightly for 3 options of rest type
					break;
				case 5:
					Services.addRider(UserInput.s); // doesnt add yet to Delivery System
					break;
				case 6:
					Services.assignOrderToRider(DS.getRiders(), DS.getOrders());
					break;
			}
		}
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
