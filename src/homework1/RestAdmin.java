package homework1;

import Utils.Validation;

public class RestAdmin extends Admin{
	private Restaurant[] restaurants;
	private int restaurantCount;
	
	public RestAdmin(String name, String username, String password, Restaurant[] restaurants, int restaurantCount) {
		super(name, username, password);
		this.restaurants = restaurants;
		this.restaurantCount = restaurantCount;
	}

	public Restaurant[] getRestaurants() {return restaurants;}
	public int getRestaurantCount() {return restaurantCount;}
	
	
	public void setRestaurants(Restaurant[] restaurants) {
		if (restaurants != null) {
			this.restaurants = restaurants;
		}
	}

	public void setRestaurantCount(int restaurantCount) {
		boolean valid = Validation.validate(restaurantCount, "invalid restaurant count");
		if(valid) this.restaurantCount = restaurantCount;
	}
	
}