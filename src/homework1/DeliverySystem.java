package homework1;

import java.util.Arrays;

import Utils.Validation;

public class DeliverySystem {
	private Customer[] customers;
	private RestAdmin[] restaurantAdmins;
	private Restaurant[] restaurants;
	private Rider[] riders;
	private Order[] orders;
	
	private int customerCount;
	private int restaurantAdminCount;
	private int restaurantCount;
	private int riderCount;
	private int orderCount;
	
	public DeliverySystem(){
		this.customers = new Customer[0];
		this.customerCount = 0;
		
		this.restaurantAdmins = new RestAdmin[0];
		this.restaurantAdminCount = 0;
		
		this.restaurants = new Restaurant[0];
		this.restaurantCount = 0;

		this.riders = new Rider[0];
		this.riderCount = 0;
		
		this.orders = new Order[0];
		this.orderCount = 0;
	}
	
	public void addCustomer(Customer customer){
		// check if already in array
		boolean valid = Validation.validateNotInArray(customer, this.customers, "customer already registered");
		
		if(valid) {
			// add
			this.customers = Arrays.copyOf(this.customers, this.customerCount + 1);
			this.customers[++this.customerCount] = customer;
		}
		
	}
	
	public void addRestaurantAdmin(RestAdmin restaurantAdmin){
		// check if already in array
		boolean valid = Validation.validateNotInArray(restaurantAdmin, this.restaurantAdmins, "restaurantAdmin already registered");
		
		if(valid) {			
			// add
			this.restaurantAdmins = Arrays.copyOf(this.restaurantAdmins, this.restaurantAdminCount + 1);
			this.restaurantAdmins[++this.restaurantAdminCount] = restaurantAdmin;
		}
		
	}
	
	public void addRetaurant(Restaurant restaurant){
		// check if already in array
		boolean valid = Validation.validateNotInArray(restaurant, this.restaurants, "restaurant already registered");
		
		if(valid) {
			// add
			this.restaurants = Arrays.copyOf(this.restaurants, this.restaurantCount + 1);
			this.restaurants[++this.restaurantCount] = restaurant;
		}
		
	}
	
	public void addRider(Rider rider){
		// check if already in array
		boolean valid = Validation.validateNotInArray(rider, this.riders, "rider already registered");
		
		if(valid) {			
			// add
			this.riders = Arrays.copyOf(this.riders, this.riderCount + 1);
			this.riders[++this.riderCount] = rider;
		}
		
	}
	
	public void addOrder(Order order){
		// check if already in array
		boolean valid = Validation.validateNotInArray(order, this.orders, "restaurantAdmin already registered");
		
		if(valid) {			
			// add
			this.orders = Arrays.copyOf(this.orders, this.orderCount + 1);
			this.orders[++this.orderCount] = order;
		}
		
	}
	
}
