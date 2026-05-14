package homework1;

import java.util.Arrays;

import Utils.UserInput;
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
	
	public Customer[] getCustomers() {return customers;}
	public RestAdmin[] getRestaurantAdmins() {return restaurantAdmins;}
	public Restaurant[] getRestaurants() {return restaurants;}
	public Rider[] getRiders() {return riders;}
	public Order[] getOrders() {return orders;}



	public void addCustomer(Customer customer){
		// check if already in array
		boolean valid = Validation.validateNotInArray(customer, this.customers, "customer already registered");
		
		if(valid) {
			this.customers = Arrays.copyOf(this.customers, this.customerCount + 1);
			this.customers[this.customerCount++] = customer;
		}
	}
	
	public void addRestaurantAdmin(RestAdmin restaurantAdmin){
		// check if already in array
		boolean valid = Validation.validateNotInArray(restaurantAdmin, this.restaurantAdmins, "restaurantAdmin already registered");
		
		if(valid) {			
			this.restaurantAdmins = Arrays.copyOf(this.restaurantAdmins, this.restaurantAdminCount + 1);
			this.restaurantAdmins[this.restaurantAdminCount++] = restaurantAdmin;
		}
	}
	
	public void addRetaurant(Restaurant restaurant){
		// check if already in array
		boolean valid = Validation.validateNotInArray(restaurant, this.restaurants, "restaurant already registered");
		
		if(valid) {
			this.restaurants = Arrays.copyOf(this.restaurants, this.restaurantCount + 1);
			this.restaurants[this.restaurantCount++] = restaurant;
		}	
	}
	
	public void addRider(Rider rider){
		// check if already in array
		boolean valid = Validation.validateNotInArray(rider, this.riders, "rider already registered");
		
		if(valid) {			
			this.riders = Arrays.copyOf(this.riders, this.riderCount + 1);
			this.riders[this.riderCount++] = rider;
		}
	}
	
	public void addOrder(Order order){
		// check if already in array
		boolean valid = Validation.validateNotInArray(order, this.orders, "restaurantAdmin already registered");
		
		if(valid) {
			this.orders = Arrays.copyOf(this.orders, this.orderCount + 1);
			this.orders[this.orderCount++] = order;
		}
	}
	
	public void displayAllOrders(Rider rider) {
		Order[] orders = rider.getOrders();
		
		if(orders == null || orders.length == 0) {
			System.out.println("no orders to display");
			return;
		}
		
		System.out.println("your orders are: ");
		for (int i = 0; i < orders.length; i++) {
			System.out.println((i+1) + ". " + orders[i]);
		}
	}
	
	public void displayAllOrders(Customer customer) {
		//get customer orders
		Order[] orders = new Order[0];
		for (Order order : this.orders) {
			if(order.getCustomerCode() == customer.getCustomerCode()) {
				orders = Arrays.copyOf(orders, orders.length + 1);
				orders[orders.length - 1] = order;
			}
		}
		
		if(orders == null || orders.length == 0) {
			System.out.println("no orders to display");
			return;
		}
		
		System.out.println("your orders are: ");
		for (int i = 0; i < orders.length; i++) {
			System.out.println((i+1) + ". " + orders[i]);
		}
	}
	
	// get restaurant code and display its info
	public void displayRestaurantDetails() {
		int restaurantCode = UserInput.getInt("restaurant code");
		
		Restaurant restaurant = null;
		for (Restaurant r: this.restaurants) {
			if(r.getRestaurantCode() == restaurantCode) {
				restaurant = r;
				break;
			}
		}
		
		if(restaurant == null) {
			System.out.println("restaurant not found.");
			return;
		}
		System.out.println(restaurant);
	}
	
	
	
}
