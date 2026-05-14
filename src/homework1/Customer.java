package homework1;

import Utils.User;
import Utils.UserInput;
import Utils.Validation;

public class Customer {
	private int customerCode;
	private String firstName;
	private String lastName;
	private String adress;
	private String phoneNumber;
	private String email;
	private double remainingCredit;
	
	private static int customerCount = 1;
	
	public int getCustomerCode() { return customerCode; }
	public String getfirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getAdress() { return adress; }
	public String getPhoneNumber() { return phoneNumber; }
	public String getEmail() { return email; }
	public double getRemainingCredit() { return remainingCredit; }

	
	public Customer(String firstName, String lastName, String adress, String phoneNumber, String email, double remainingCredit) {
		this.customerCode = customerCount++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.remainingCredit = remainingCredit;
		
		System.out.println("customer with code: " + this.customerCode + " has been created.");
	}
	
	public void setfirstName(String firstName) {
		boolean valid = Validation.validate(firstName, "invalid firstName");
		
		if (valid) this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		boolean valid = Validation.validate(lastName, "invalid lastName");
		
		if (valid) this.lastName = lastName;
	}
	public void setAdress(String adress) {
		boolean valid = Validation.validate(adress, "invalid adress");
		
		if (valid) this.adress = adress;
	}
	public void setPhoneNumber(String phoneNumber) {
		boolean valid = Validation.isPhoneNumber(phoneNumber);
		
		if (valid) this.phoneNumber = phoneNumber;
		else System.out.println("invalid phone number entered");
	}
	public void setEmail(String email) {
		boolean valid = Validation.validate(email, "invalid email");
		
		if (valid) this.email = email;
	}
	public void setRemainingCredit(double remainingCredit) {
		boolean valid = Validation.validate(remainingCredit, "invalid remainingCredit");
		
		if (valid) this.remainingCredit = remainingCredit;
	}
	
	public void menu(DeliverySystem DS) {
		System.out.println("you are customer");
		
		while(true) {
			System.out.println("1. create new order");
			System.out.println("2. view my orders");
			System.out.println("3. update my personal info");
			System.out.println("4. view restaurant info");
			System.out.println("5. logout");
			
			int option = UserInput.getIntFromRange(1,5, "option");
			if(option == 5) break;
			
			switch (option) {
				case 1:
					Services.createNewOrder(this, DS.getRestaurants());
					break;
				case 2:
					DS.displayAllOrders(this);
					break;
				case 3:
					Services.updatePersonalInfo(this);
					break;
				case 4:
					DS.displayRestaurantDetailsByCode();
					break;
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Customer) {
			Customer other = (Customer) obj;
			return other.customerCode == this.customerCode;
		}
		return false;
	}
}
