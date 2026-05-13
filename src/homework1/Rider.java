package homework1;

import Utils.Validation;

public class Rider {
	private int id;
	private String fullName;
	private int phoneNumber;
	private String vehicle;
	private boolean isAvailable;
	private Order[] orders;
	
	
	public int getId() {return id;}
	public String getFullName() {return fullName;}
	public int getPhoneNumber() {return phoneNumber;}
	public String getVehicle() {return vehicle;}
	public boolean isAvailable() {return isAvailable;}
	public Order[] getOrders() {return orders;}

	
	public void setId(int id) {
		this.id = id;
	}

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


	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Rider) {
			Rider other = (Rider) obj;
			return other.id == this.id;
		}
		return false;
	}
}