package homework1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import Utils.UserInput;
import Utils.Validation;

public class DeliveryDataBase {
	private Admin systemAdministrator;
	private ArrayList<Customer> customers;
	private ArrayList<RestAdmin> restaurantAdmins;
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Rider> riders;
	private ArrayList<Order> orders;
	
	private HashMap<Integer, ArrayList<Order>> customerOrders;
	private Hashtable<Integer, ArrayList<Restaurant>> customersOrderedRestaurants;
	private HashMap<Integer, Double> customerExpenses;
	
	
	public DeliveryDataBase(){
		this.systemAdministrator = new Admin("Steve", "admin", "12345");
		
		this.customers = new ArrayList<Customer>();
		this.restaurantAdmins = new ArrayList<RestAdmin>();
		this.restaurants = new ArrayList<Restaurant>();
		this.riders = new ArrayList<Rider>();
		this.orders = new ArrayList<Order>();
		
		this.customerOrders = new HashMap<Integer, ArrayList<Order>>();
		this.customersOrderedRestaurants = new Hashtable<Integer, ArrayList<Restaurant>>();
		this.customerExpenses = new HashMap<Integer, Double>();
	}
	
	public Admin getSystemAdministrator() { return systemAdministrator; }
	public ArrayList<Customer> getCustomers() { return customers; }
	public ArrayList<RestAdmin> getRestaurantAdmins() { return restaurantAdmins; }
	public ArrayList<Restaurant> getRestaurants() { return restaurants; }
	public ArrayList<Rider> getRiders() { return riders; }
	public ArrayList<Order> getOrders() { return orders; }
	public HashMap<Integer, ArrayList<Order>> getCustomerOrders() { return customerOrders; }
	public Hashtable<Integer, ArrayList<Restaurant>> getCustomersOrderedRestaurants() { return customersOrderedRestaurants; }
	public HashMap<Integer, Double> getCustomerExpenses() { return customerExpenses; }

	public void addCustomer(Customer customer){
		// check if already in array
		boolean valid = Validation.validateNotInArray(customer, this.customers);
		
		if(valid) {			
			this.customers.add(customer);
		}
		else System.out.println("invalid, customer already registered");
	}
	
	public void addRestaurantAdmin(RestAdmin restaurantAdmin){
		// check if already in array
		boolean valid = Validation.validateNotInArray(restaurantAdmin, this.restaurantAdmins);
		
		if(valid) {
			this.restaurantAdmins.add(restaurantAdmin);
		} 
		else System.out.println("invalid restaurant admin already registered");
	}
	
	public void addRestaurant(Restaurant restaurant){
		// check if already in array
		boolean valid = Validation.validateNotInArray(restaurant, this.restaurants);
		
		if(valid) {
			this.restaurants.add(restaurant);
			
			System.out.println("restaurant with code: " + restaurant.getRestaurantCode() + " has been created.");
		}
		else System.out.println("invalid, restaurant already registered");
	}
	
	public void addRider(Rider rider){
		// check if already in array
		boolean valid = Validation.validateNotInArray(rider, this.riders);

		if(valid) {
			this.riders.add(rider);
		}
		else System.out.println("invalid,  rider already registered");
	}
	
	public void addOrder(Order order){
		// check if already in array
		boolean valid = Validation.validateNotInArray(order, this.orders);
		
		if(valid) {
			this.orders.add(order);
		}
		else System.out.println("invalid,  order already registered");
	}
	
	public void displayAllOrders(Rider rider) {
		ArrayList<Order> orders = rider.getOrders();
		
		if(orders == null || orders.size() == 0) {
			System.out.println("no orders to display");
			return;
		}
		
		System.out.println("your orders are: ");
		for (int i = 0; i < orders.size(); i++) {
			System.out.println((i+1) + ". " + orders.get(i));
		}
		
		System.out.println("-----------------------------");
	}
	
	public void displayAllOrders(Customer customer) {
		ArrayList<Order> customerOrders = this.customerOrders.get(customer.getCustomerCode());
		
		if(customerOrders == null || customerOrders.size() == 0) {
			System.out.println("Customer has no orders.");
			return;
		}
		
		System.out.println("your orders are: ");
		for (int i = 0; i < customerOrders.size(); i++) {
			System.out.println((i+1) + ". " + customerOrders.get(i));
		}
		
		System.out.println("-----------------------------");
	}
	
	// get restaurant code and display its info
	public void displayRestaurantDetailsByCode() {
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
	
	public void addOrderToCustomer(int customerCode, Order order) {
		if (!this.customerOrders.containsKey(customerCode)) {
			this.customerOrders.put(customerCode, new ArrayList<Order>());
		}
		if (this.customerOrders.get(customerCode).contains(order)) {
			System.out.println("order alraedy exsist for the customer");
			return;
		}
		this.customerOrders.get(customerCode).add(order);
		System.out.println("order was conected to customer");
	}
	
	public ArrayList<Order> riderOrders(String riderCode){
		ArrayList<Order> ordersToReturn = new ArrayList<Order>();
		ArrayList<Order> listOfOrders = new ArrayList<Order>();
		for (Rider rider : this.riders) {
			if (riderCode.equals(rider.getId())) {
				listOfOrders = rider.getOrders();
				break;
			}
		}
		for (Order order : listOfOrders) {
			if (order.getDeliveryStatus().equals("on the way") || order.getDeliveryStatus().equals("sent")) {
				ordersToReturn.add(order);
			}
		}
		return ordersToReturn;
	}
	
	public ArrayList<Restaurant> customerOrdersFromPremiumRest(Customer customer){
		ArrayList<Restaurant> listToReturn = new ArrayList<Restaurant>();
		
		if (!this.customersOrderedRestaurants.containsKey(customer.getCustomerCode())) {
			return listToReturn;
		}
		
		for (Restaurant r : this.customersOrderedRestaurants.get(customer.getCustomerCode())) {
			if (r instanceof PremiumRestaurant) {
				listToReturn.add(r);
			}
		}
		return listToReturn;
	}
	
	public Customer customerWithHighestOrders() {
		int currntHighstId = -1;
		int currntHighstOrders = -1;
		for (Integer customerId : customerOrders.keySet()) {
			int cuurntSum = customerOrders.get(customerId).size();
			if (cuurntSum > currntHighstOrders) {
				currntHighstId = customerId;
				currntHighstOrders = cuurntSum;
			}
		}
		for (Customer customer : this.customers) {
			if (currntHighstId == customer.getCustomerCode()) {
				return customer;
			}
		}
		return null; // למקרה ואין בכלל לקוחות
	}
	
	
	public Rider riderWithHighstOrders() {
		Rider bestRider = null;
		int currntHighstOrders = -1;
		for (Rider rider : this.riders) {
			int ridersOrdersCunt = rider.getOrders().size();
			if (ridersOrdersCunt > currntHighstOrders) {
				bestRider = rider;
				currntHighstOrders = ridersOrdersCunt;
			}
		}
		return bestRider;
	}
	
	public ArrayList<Restaurant> openRestaurantsByKitchenType(String kitchenType){
		ArrayList<Restaurant> openRst = new ArrayList<Restaurant>();
		for (Restaurant restaurant : this.restaurants) {
			if (restaurant.getKitchenType().equals(kitchenType) && restaurant.isOpen()) {
				openRst.add(restaurant);
			}
		}
		return openRst;
	}
	
	
	
}