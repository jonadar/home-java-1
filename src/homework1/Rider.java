package homework1;

import Utils.UserInput;
import Utils.Validation;

public class Rider {
	private String id;
	private String fullName;
	private String phoneNumber;
	private String vehicle;
	private boolean isAvailable;
	private Order[] orders;
	
	public Rider(String id, String fullName, String phoneNumber, String vehicle, boolean isAvailable) {
		this.id = id;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.vehicle = vehicle;
		this.orders = new Order[0];
		this.isAvailable = isAvailable;
	}
	
	public String getId() { return id; }
	public String getFullName() { return fullName; }
	public String getPhoneNumber() { return phoneNumber; }
	public String getVehicle() { return vehicle; }
	public Order[] getOrders() { return orders; }

	public void setId(String id) {
		boolean valid = Validation.isId(fullName);
		if (valid) this.id = id;
		else {
			System.out.println("invalid id");
		}
	}
	
	public void setFullName(String fullName) {
		boolean valid = Validation.validate(fullName, "invalid full name");
		if (valid) this.fullName = fullName;
	}

	public void setPhoneNumber(String phoneNumber) {
		boolean valid = Validation.isPhoneNumber(phoneNumber);
		if (valid) this.phoneNumber = phoneNumber;
		else {
			System.out.println("invalid phone number");
		}
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
			System.out.println("3. logout");
			int option = UserInput.getIntFromRange(1, 3, "option");
			if(option == 3) break;
			
			switch (option) {
				case 1:
					Services.updateOrderStatus(this);
					break;
				case 2:
					DS.displayAllOrders(this);
					break;
			}
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