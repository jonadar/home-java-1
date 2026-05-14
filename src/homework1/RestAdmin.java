package homework1;

import java.util.Arrays;

public class RestAdmin extends Admin{
	private Restaurant[] restaurants;
	private int restaurantCount;
	
	public RestAdmin(String name, String username, String password) {
		super(name, username, password);
		this.restaurants = new Restaurant[0];
		this.restaurantCount = 0;
	}

	public Restaurant[] getRestaurants() {return restaurants;}
	public int getRestaurantCount() {return restaurantCount;}
	
	public boolean addRestaurant(Restaurant rest) {
		if(rest == null) return false;
		for(Restaurant r: this.restaurants) {
			if(rest.equals(r)) {
				System.out.println("restaurant already in array");
				return false;
			}	
		}
		this.restaurants =	Arrays.copyOf(this.restaurants, this.restaurantCount+1);
		this.restaurants[this.restaurantCount++] = rest;
		return true;
	}
	
}
