package homework1;

import Utils.UserInput;
import Utils.Validation;

public class Rider {
	private static int counter = 1;
	private int id;
	private String fullName;
	private int phoneNumber;
	private String vehicle;
	private boolean isAvailable;
	private Order[] orders;
	
	public Rider(String fullName, int phoneNumber, String vehicle) {
		this.id = counter++; // supposed to be id 9 digits not automatic
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.vehicle = vehicle;
		this.orders = new Order[0];
		this.isAvailable = true;
	}
	
	public int getId() { return id; }
	public String getFullName() { return fullName; }
	public int getPhoneNumber() { return phoneNumber; }
	public String getVehicle() { return vehicle; }
	public boolean isAvailable() { return isAvailable; }
	public Order[] getOrders() { return orders; }

	public void setFullName(String fullName) {
		boolean valid = Validation.validate(fullName, "invalid full name");
		if (valid) this.fullName = fullName;
	}

	public void setPhoneNumber(int phoneNumber) {
		boolean valid = Validation.validate(phoneNumber, "invalid phone number");
		if (valid) this.phoneNumber = phoneNumber;
	}

	public void setVehicle(String vehicle) {
		boolean valid = Validation.validate(vehicle, "invalid vehicle");
		if (valid) this.vehicle = vehicle;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setOrders(Order[] orders) {
		if (orders != null) this.orders = orders;
	}
	
	public void menu(DeliverySystem DS) {
		System.out.println("you are rider");
		while(true) {
			System.out.println("1. update order status");
			System.out.println("2. view orders");
			int riderOption = UserInput.getInt("option");
			if(riderOption == 7) break;
			// validate option
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Rider) {
			Rider other = (Rider) obj;
			return other.id == this.id;
		}
		return false;
	}
}