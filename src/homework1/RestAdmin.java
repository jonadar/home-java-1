package homework1;

import Utils.Validation;

public class RestAdmin extends Admin{
	private Restaurant[] restaurants;
	private int restaurantCount;
	
	public RestAdmin(String name, String username, String password) {
		super(name, username, password);
		this.restaurants = new Restaurant[0];
		this.restaurantCount = 0;
	}

	public Restaurant[] getRestaurants() { return restaurants; }
	public int getRestaurantCount() { return restaurantCount; }
}