package homework1;

import java.util.Arrays;

import Utils.UserInput;

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
	
	@Override
	public void menu(DeliverySystem DS) {
		while(true) {
			System.out.println("1. add customer");
			System.out.println("2. create new order");
			System.out.println("3. add rider");
			System.out.println("4. assign rider to order");
			System.out.println("5. logout");
			
			int option = UserInput.getIntFromRange(1, 5, "option");
			if(option == 5) break;
			
			switch (option) {
				case 1:
					Services.addCustomer(UserInput.s, DS.getCustomers()); // doesnt actually add yet, just creates
					break;
				case 2:
//					Customer customer = DS.chooseCustomer();
//					Services.createNewOrder(customer, DS.getRestaurants());
					break;
				case 3:
					break;
				case 4:
					break;
			}
		}
	}
	
}
