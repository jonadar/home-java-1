package homework1;

import java.util.Arrays;

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
		
		System.out.println("rider with id: " + this.id + " has been created.");
	}
	
	public String getId() { return id; }
	public String getFullName() { return fullName; }
	public String getPhoneNumber() { return phoneNumber; }
	public String getVehicle() { return vehicle; }
	public Order[] getOrders() { return orders; }
	public boolean getAvailable() { return isAvailable; }

	public void setId(String id) {
		if (Validation.isId(fullName)) this.id = id;
		else System.out.println("invalid id");
	}
	
	public void setFullName(String fullName) {
		if (Validation.isName(fullName)) this.fullName = fullName;
		else System.out.println("invalid full name");
	}

	public void setPhoneNumber(String phoneNumber) {
		if (Validation.isPhoneNumber(phoneNumber)) this.phoneNumber = phoneNumber;
		else System.out.println("invalid phone number");
	}

	public void setVehicle(String vehicle) {
		if (Validation.isName(vehicle)) this.vehicle = vehicle;
		else System.out.println("invalid vehicle");
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setOrders(Order[] orders) {
		if (orders == null) {
			System.out.println("cant set null orders");
			return;
		}
		
		for (Order order : orders) {
			if (order == null) {
				System.out.println("cant set orders, null value in array.");
				return;
			} else if (!Validation.validateNotInArray(order, orders)) {
				System.out.println("cant set orders, duplicate in array.");
				return;
			}
		}

		this.orders = orders;
	}
	
	public boolean addOrder(Order order) {
		if(order == null) return false;
		if(!Validation.validateNotInArray(order, this.orders)) {
			System.out.println("order already in riders list");
			return false;
		}
		
		this.orders = Arrays.copyOf(this.orders, this.orders.length + 1);
		this.orders[this.orders.length - 1] = order;
		return true;
		
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
			return other.id.equals(this.id);
		}
		return false;
	}
}