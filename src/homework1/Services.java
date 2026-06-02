package homework1;

import java.util.ArrayList;

import Utils.UserInput;
import Utils.Validation;

public class Services {
	
	public static void main(String[] args) {}

	
	public static void updateOrderStatus(Rider rider) {
		ArrayList<Order> orders = rider.getOrders();
		
		if(orders == null || orders.size() == 0) {
			System.out.println("no orders to update");
			return;
		}
		
		// pick order from list
		displayArrayAsNumberedList(orders);
		
		int orderCode = UserInput.getInt("order code");
		Order order = null;
		for (Order o: orders) {
			if(o.getOrderCode() == orderCode) {
				order = o;
				break;
			}
		}
		
		if(order == null) {
			System.out.println("order not found.");
			return;
		}

		// choose order status (on the way), (delivered)
		final String[] options = {"on the way", "delivered"};
		String deliveryOption = UserInput.getStringFromOptions(options);
		
		order.setDeliveryStatus(deliveryOption);
		
		// if chose (delivered) update order delivery date
		if (deliveryOption.equals("delivered")) {
			String deliveryDate = UserInput.getDate("delivery date");
			order.setDeliveryDate(deliveryDate);
		}
		
		System.out.println("updated order.");
	}
	
	public static void updatePersonalInfo(Customer customer) {
		System.out.println("what would you like to update?");
		final String[] options = {"phone number", "adress", "email", "none"};
		String userSelection = UserInput.getStringFromOptions(options);
		
		if(userSelection.equals(options[0])) { // phone number
			String phoneNumber = UserInput.getPhoneNumber();
			customer.setPhoneNumber(phoneNumber);
			System.out.println("updated phone number");
		}
		
		else if (userSelection.equals(options[1])){ // adress
			String adress = UserInput.getAddress();
			customer.setAddress(adress);
			System.out.println("updated address");
		}
		
		else if (userSelection.equals(options[2])){ // email
			String email = UserInput.getEmail();
			customer.setEmail(email);
			System.out.println("updated email address");
		}
	}
	
	/**
	 * @param customer who's order it is
	 * @param restaurants array of available restaurants
	 * @return Order if created successfully otherwise null
	 */
	public static Order createNewOrder(Customer customer, ArrayList<Restaurant> restaurants) {
		if(restaurants.size() == 0) {
			System.out.println("no restaurants to order from");
			return null;
		}
		
		displayArrayAsNumberedList(restaurants);
		
		// choose restaurant
		int restaurantIndex = UserInput.getIntFromRange(1, restaurants.size(), "restaurant");
		Restaurant restaurant = restaurants.get(restaurantIndex-1);
		
		// get base amount
		double baseCost = UserInput.getDouble("base cost");
		
		// check customer has enough money
		if(customer.getRemainingCredit() < restaurant.calculatePrice(baseCost)) {
			System.out.println("customer does not have enough remaining credit to place order. cost is " + restaurant.calculatePrice(baseCost));
			return null;
		}
		
		if (restaurant instanceof PremiumRestaurant) {
			if(baseCost < ((PremiumRestaurant) restaurant).getMinOrderValue()) {				
				System.out.println("cost too low for order, must be more than " + ((PremiumRestaurant) restaurant).getMinOrderValue());
				return null;	
			}
		}
		
		// get date
		String date = UserInput.getDate("todays date");
		
		return new Order(customer.getCustomerCode(), restaurant, baseCost, date);
	}
	
	/** 
	 * @param customer code
	 * @param customerArray
	 * @return true if customer is not in array
	 */
	public static boolean notInAnArray(int code, ArrayList<Customer> customerArray) {
		for (int i = 0; i < customerArray.size(); i++) {
			if (customerArray.get(i) != null && customerArray.get(i).getCustomerCode() == code) {
				System.out.println("invalid field");
				return false;
			}
		}
		return true;
	}
	
	/** 
	 * @param code username of admin
	 * @param restAdminArray
	 * @return true if restAdmin is not in array
	 */
	public static boolean notInAnArray(String code, ArrayList<RestAdmin> RestAdminArray) {
		for (int i = 0; i < RestAdminArray.size(); i++) {
			if (RestAdminArray.get(i) != null && RestAdminArray.get(i).getUsername().equals(code)) {
				System.out.println("invalid field");
				return false;
			}
		}
		return true;
	}
	
	
	public static Customer addCustomer() { // TODO: add customer to Database array
		String name = UserInput.getName("name");
		
		String famillyName = UserInput.getName("familly name");
		
		String adress = UserInput.getAddress();
		
		String phoneNumber = UserInput.getPhoneNumber();
		
		String email = UserInput.getEmail();
		
		double remainingCredit = UserInput.getDouble("remaining credit");
		return new Customer(name, famillyName, adress, phoneNumber, email, remainingCredit);
	}
		
		
		
	public static RestAdmin addRestAdmin() { // TODO: add rest admin to Database array
		String username = UserInput.getUsername();
	
		String restAdminName = UserInput.getName("resturnt admin name");
		
		String password = UserInput.getPassword();
		
		return new RestAdmin(restAdminName, username, password);
	}
		
		
	// TODO: swap these with contains
	public static RestAdmin findRestAdmin(String username, ArrayList<RestAdmin> restaurantAdmins) {
		for (int i = 0 ; i<restaurantAdmins.size() ; i++) {
			if (restaurantAdmins.get(i) != null) {
				if (username.equals(restaurantAdmins.get(i).getUsername())) {
					return restaurantAdmins.get(i);
				}
			}
		}
		return null;
	}
	
	public static Restaurant findRestaurant(int restCode, ArrayList<Restaurant> restaurants) {
		for (int i = 0 ; i<restaurants.size() ; i++) {
			if (restaurants.get(i) != null) {
				if (restCode == restaurants.get(i).getRestaurantCode()) {
					return restaurants.get(i);
				}
			}
		}
		return null;
	}
	
	public static boolean assignRestAdminToRestaurant(ArrayList<RestAdmin> restaurantAdmins, ArrayList<Restaurant> restaurants) {
		RestAdmin restAdmin = findRestAdmin(UserInput.getUsername(), restaurantAdmins);
		Restaurant restaurant = findRestaurant(UserInput.getInt("restaurant code"), restaurants);
		if (restAdmin!= null && restaurant!=null) {
			boolean success = restAdmin.addRestaurant(restaurant);
			if (success) {
				System.out.println("assigned admin " + restAdmin.getUsername() + " to restaurant " + restaurant.getName());
				return true;
			} else return false;
		}
		System.out.println("the restaurant admin or the restaurant can not be found");
		return false;
	}
	
	public static Restaurant addRestaurant() {
		System.out.println("choose 1 for restaurant");
		System.out.println("choose 2 for fast food restaurant");
		System.out.println("choose 3 for premium restaurant");
		
		int option = UserInput.getIntFromRange(1, 3, "restaurant type");
		
		Restaurant restaurant = null;
		
		switch (option) {
			case 1: // הוספת מסעדה רגילה
				System.out.println("you choose restaurant");
				restaurant = createRestaurant();
				break;
			case 2: // הוספת מסעדה מהירה
				System.out.println("you choose fast food restaurant");
				restaurant = createFastFoodRestaurant();
				break;
			case 3: // הוספת מסעדת יוקרה
				System.out.println("you choose premium restaurant");
				restaurant = createPremiumRestaurant();
				break;
			default:
				System.out.println("invalid number");
		}
		
		return restaurant;
	}
	
	public static Restaurant createRestaurant() {
		String restName = UserInput.getName("resaurant name");
		
		String kitchenType = UserInput.getName("kitchen type");
		
		double rating = UserInput.getDoubleFromRange(0, 10, "rating");

		boolean isOpen = UserInput.getBoolean("is restaurant open?");
		
		double deliveryFee = UserInput.getDouble("delivery fee");

		return new Restaurant(restName, kitchenType, rating, isOpen, deliveryFee);
	}
	
	public static PremiumRestaurant createPremiumRestaurant() {
		Restaurant basicRest = createRestaurant();
		
		double minOrderValue = UserInput.getDouble("minimum order value");
		
		double orderFeePercentage = UserInput.getDouble("order fee percentage");

		return new PremiumRestaurant(basicRest.getName(), basicRest.getKitchenType(), basicRest.getRating(), basicRest.isOpen(), basicRest.getDeliveryFee(), minOrderValue, orderFeePercentage);
	}
	
	public static FastFoodRestaurant createFastFoodRestaurant() {
		Restaurant basicRest = createRestaurant();
		double averageCookTime = UserInput.getDouble("average cook time");
		
		double fastDeliveryFee = UserInput.getDouble("fast delivery fee");
		
		return new FastFoodRestaurant(basicRest.getName(), basicRest.getKitchenType(), basicRest.getRating(), basicRest.isOpen(), basicRest.getDeliveryFee(), averageCookTime, fastDeliveryFee);
	}
	
	public static Rider addRider() {
		String id = UserInput.getId();
	
		String fullName = UserInput.getName("full name");
		
		String phoneNumber = UserInput.getPhoneNumber();
		
		String vehicle = UserInput.getName("vehicle");
		
		boolean isAvailable = UserInput.getBoolean("is is available");
		
		return new Rider(id, fullName, phoneNumber, vehicle, isAvailable);
	}
	
	public static Rider findRider(String id, ArrayList<Rider> riders) {
		if(!Validation.isId(id)) {
			return null;
		}
		
		for (Rider rider :riders) {
			if (rider != null && id.equals(rider.getId())) {
				return rider;
			}
		}
		
		return null;
	}
	
	public static Order findOrder(int orderCode, ArrayList<Order> orders) {
		for (int i = 0; i < orders.size() ; i++) {
			if (orders.get(i) != null) {
				if (orderCode == orders.get(i).getOrderCode()) {
					return orders.get(i);
				}
			}
		}
		return null;
	}
	
	public static boolean assignOrderToRider(ArrayList<Rider> riders, ArrayList<Order> orders) {
		Rider rider = findRider(UserInput.getId(), riders);
		Order order = findOrder(UserInput.getInt("order"), orders);
		
		if (rider == null || order == null) {
			System.out.println("rider or order could not be found");
			return false;
		}
		
		// rider is available and order does not have a rider assigned yet
		if (rider.getAvailable() && order.getDriverId() == null){
			boolean added = rider.addOrder(order);
			if(added) {
				System.out.println("assigned rider " + rider.getId() + " to order " + order.getOrderCode());
				// update order
				order.setDriverId(rider.getId());
				order.setDeliveryStatus("on the way");
				return true;
			} else {
				System.out.println("failed to add order to rider");
				return false;
			}
		}
		System.out.println("the order or the rider can not be found");
		return false;
	}
	
//	 מחפש מסעדה ומוודא שהיא שייכת למנהל מסעדה ושהם קיימים
	public static boolean RestAdminAddOrder( ArrayList<RestAdmin> restaurantAdmins,  ArrayList<Restaurant> restaurants) {
		RestAdmin restAdmin = findRestAdmin(UserInput.getUsername(), restaurantAdmins);
		Restaurant restaurant = findRestaurant(UserInput.getInt("restaurant code"), restaurants);
		Restaurant restforAdmin = findRestaurant(restaurant.getRestaurantCode(), restAdmin.getRestaurants());
		if (restAdmin!= null && restforAdmin!=null) {
			return true;
		}
		System.out.println("the restaurant admins or the restaurant can not be found");
		return false;
	}
	
	public static Customer findCustomer(int customerCode, ArrayList<Customer> customers) {
		for (int i = 0 ; i<customers.size() ; i++) {
			if (customers.get(i) != null) {
				if (customerCode == customers.get(i).getCustomerCode()) {
					return customers.get(i);
				}
			}
		}
		return null;
	}
	
	public static Order createNewOrderByRestAdmin(RestAdmin restAdmin, ArrayList<Customer> customers) {
		ArrayList<Restaurant> adminRestaurants = restAdmin.getRestaurants();
		
		if(adminRestaurants.size() == 0) {
			System.out.println("no restaurants found for admin, cant add order");
			return null;
		}

		displayArrayAsNumberedList(adminRestaurants);
		
		// choose restaurant
		int restaurantIndex = UserInput.getIntFromRange(1, adminRestaurants.size(), "restaurant");
		Restaurant restaurant = adminRestaurants.get(restaurantIndex-1);
		
		//check if belongs to rest admin
//		boolean hasRestaurant = false;
//		for (Restaurant r: restAdmin.getRestaurants()) {
//			if(restaurant.equals(r)) {
//				hasRestaurant = true;
//				break;
//			}
//		}
		// TODO: this if is not really needed as we pass through admins restaurants
		if(!adminRestaurants.contains(restaurant)) {
			System.out.println("restaurant does not belong to this rest admin.");
			return null;
		}
		
		// choose customer code if it not exsist it breaks out of the func
		Customer customer = findCustomer(UserInput.getInt("customer code"), customers);
		if (customer == null) {
			System.out.println("could not find customer");
			return null;
		}
		
		// get base amount
		double baseCost = UserInput.getDouble("base cost");
		
		if(customer.getRemainingCredit() < restaurant.calculatePrice(baseCost)) {
			System.out.println("customer does not have enough remaining credit to place order. cost is " + restaurant.calculatePrice(baseCost));
			return null;
		}
		if (restaurant instanceof PremiumRestaurant) {
			if(baseCost < ((PremiumRestaurant) restaurant).getMinOrderValue()) {				
				System.out.println("cost too low for order, must be more than " + ((PremiumRestaurant) restaurant).getMinOrderValue());
				return null;
			}
		}
		
		// get date
		String date = UserInput.getDate("todays date");
		
		return new Order(customer.getCustomerCode(), restaurant, baseCost, date);
	}
	
	// get restaurant code and decide if open or closed
	public static void updateRestaurantStatus(ArrayList<Restaurant> restaurants) {
		Restaurant restaurant = findRestaurant(UserInput.getInt("restaurant code"), restaurants);
		
		if(restaurant == null) System.out.println("restaurant not found");
		
		String currently = restaurant.isOpen() ? "open" : "closed";
		String canBe = restaurant.isOpen() ? "closed" : "open";
		
		System.out.println("restaurant is currently " + currently);
		boolean shouldChange = UserInput.getBoolean("set restaurant as " + canBe + "?");
		
		if(shouldChange) restaurant.setOpen(!restaurant.isOpen()); // if user asks to change, change to opposit of what it has
	}
	
	/**
	 * @param <T>
	 * @param items array to print with numbers
	 */
	public static <T> void displayArrayAsNumberedList(ArrayList<T> items) {
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i+1) + ". " + items.get(i));
		}
	}
	
	/**
	 * @param customer to add to balance
	 */
	public static void chargeCustomerBalance(Customer customer) {
		double chargeAmount = UserInput.getDouble("amount to add");
		customer.setRemainingCredit(customer.getRemainingCredit() + chargeAmount);
	}
	
	/**
	 * @param customer to withdraw from balance
	 */
	public static void withdrawCustomerBalance(Customer customer) {
		double withdrawAmount = UserInput.getDoubleFromRange(0, customer.getRemainingCredit(), "amount to withdraw");
		customer.setRemainingCredit(customer.getRemainingCredit() - withdrawAmount);
	}
	
}