package homework1;

import Utils.Validation;

public class Order {
	private int orderCode;
	private int customerCode;
	private Restaurant restaurant;
	private int restaurantCode;
	private int driverId;
	private String orderDate;
	private String deliveryDate;
	private double basePrice;
	private double finalPrice;
	private String deliveryStatus = "sent"; // sent, on the way, delivered
	
	private static int orderCount = 1;
	
	public int getOrderCode() {return orderCode;}
	public int getCustomerCode() {return customerCode;}
	public Restaurant getRest() {return restaurant;}
	public int getRestaurantCode() {return restaurantCode;}
	public int getDriverId() {return driverId;}
	public String getOrderDate() {return orderDate;}
	public String getDeliveryDate() {return deliveryDate;}
	public double getBasePrice() {return basePrice;}
	public double getFinalPrice() {return finalPrice;}
	public String getDeliveryStatus() {return deliveryStatus;}
	
	
	public Order(int customerCode, Restaurant restaurant, int driverId, String orderDate, double basePrice, String deliveryStatus) {
		this.orderCode = orderCount++;
		this.customerCode = customerCode;
		this.restaurant = restaurant;
		this.restaurantCode = restaurant.getRestaurantCode();
		this.driverId = driverId;
		this.orderDate = orderDate;
		this.deliveryDate = "0";
		this.basePrice = basePrice;
		// make function to set price and calculate final price
	}
	
	public void setOrderCode(int orderCode) {
		boolean valid = Validation.validate(customerCode, "invalid order code");
		if (valid) this.orderCode = orderCode;
	}
	
	public void setCustomerCode(int customerCode) {
		boolean valid = Validation.validate(customerCode, "invalid customer code");
		if(valid) this.customerCode = customerCode;
	}
	
	public void setRestaurant(Restaurant rest) {
		if (rest != null) {
			this.restaurant = rest;
		}
		else {System.out.println("invalid field");
		}
	}
	
	public void setRestaurantCode(int restaurantCode) {
		boolean valid = Validation.validate(restaurantCode, "invalid restaurant code");
		if(valid) this.restaurantCode = restaurantCode;
	}
	
	public void setDriverId(int driverId) {
		boolean valid = Validation.validate(driverId, "invalid driver id code");
		if(valid) this.driverId = driverId;
	}
	
	public void setOrderDate(String orderDate) {
		boolean valid = Validation.validate(orderDate, "invalid order date");
		if(valid) this.orderDate = orderDate;
	}
	
	public void setDeliveryDate(String deliveryDate) {
		boolean valid = Validation.validate(orderDate, "invalid delivery date");
		if(valid) this.deliveryDate = deliveryDate;
	}
	
	public void setBasePrice(double basePrice) {
		boolean valid = Validation.validate(orderDate, "invalid base price");
		if(valid) this.basePrice = basePrice;
}
	
	public void setFinalPrice(double finalPrice) {
		boolean valid = Validation.validate(orderDate, "invalid final price");
		if(valid) this.finalPrice = finalPrice;
		}
	
	public void setDeliveryStatus(String deliveryStatus) {
		boolean valid = Validation.validate(orderDate, "invalid delivery status");
		if(valid) this.deliveryStatus = deliveryStatus;
	}
	
	@Override
	public String toString() {
		return "Order [orderCode=" + orderCode + ", customerCode=" + customerCode + ", restaurant=" + restaurant
				+ ", restaurantCode=" + restaurantCode + ", driverId=" + driverId + ", orderDate=" + orderDate
				+ ", deliveryDate=" + deliveryDate + ", basePrice=" + basePrice + ", finalPrice=" + finalPrice
				+ ", deliveryStatus=" + deliveryStatus + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Order) {
			Order other = (Order) obj;
			return other.orderCode == this.orderCode;
		}
		return false;
	}
}
