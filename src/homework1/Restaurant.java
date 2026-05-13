package homework1;

import Utils.Validation;

public class Restaurant {
	private int restaurantCode;
	private String name;
	private String kitchenType;
	private double rating;
	private boolean isOpen;
	private double deliveryFee;
	
	private static int restaurantCount = 0;
	
	public int getRestaurantCode() { return restaurantCode; }
	public String getName() { return name; }
	public String getKitchenType() { return kitchenType; }
	public double getRating() { return rating; }
	public boolean isOpen() { return isOpen; }
	public double getDeliveryFee() { return deliveryFee; }
	
	
	public Restaurant(String name, String kitchenType, double rating, boolean isOpen,
			double deliveryFee) {
		this.restaurantCode = restaurantCount++;
		this.name = name;
		this.kitchenType = kitchenType;
		this.rating = rating;
		this.isOpen = isOpen;
		this.deliveryFee = deliveryFee;
	}
	
	public Restaurant(String name, String kitchenType) {
		this.restaurantCode = restaurantCount++;
		this.name = name;
		this.kitchenType = kitchenType;
		this.rating = 10;
		this.isOpen = false;
		this.deliveryFee = 0;
	}
	
	public void setRestaurantCode(int restaurantCode) {
		boolean valid = Validation.validate(restaurantCode, "invalid restaurantCode");
		
		if(valid) this.restaurantCode = restaurantCode;
	}
	
	public void setName(String name) {
		boolean valid = Validation.validate(name, "invalid restaurant name");
		
		if(valid) this.name = name;
	}
	
	public void setKitchenType(String kitchenType) {
		boolean valid = Validation.validate(kitchenType, "invalid kitchenType");
		
		if(valid) this.kitchenType = kitchenType;
	}
	
	public void setRating(double rating) {
		boolean valid = Validation.validate(rating, "invalid rating");
		
		if(valid) this.rating = rating;
	}
	
	public void setOpen(boolean isOpen) { 
		this.isOpen = isOpen;
	}
	
	public void setDeliveryFee(double deliveryFee) {
		//special validation?
		this.deliveryFee = deliveryFee;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Restaurant) {
			Restaurant other = (Restaurant) obj;
			return other.restaurantCode == this.restaurantCode && other.name.equals(this.name);
		}
		return false;
	}
	
}
