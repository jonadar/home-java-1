package homework1;

import Utils.UserInput;
import Utils.Validation;

public class Services {
	
	public static void main(String[] args) {}

	
	public static void updateOrderStatus(Rider rider) {
		Order[] orders = rider.getOrders();
		
		if(orders == null || orders.length == 0) {
			System.out.println("no orders to update");
			return;
		}
		
		// pick order from list
		for (int i = 0; i < orders.length; i++) {
			System.out.println((i+1) + ". " + orders[i]);
		}
		
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
		String[] options = {"on the way", "delivered"};
		String deliveryOption = UserInput.getStringFromOptions(options);
		
		order.setDeliveryStatus(deliveryOption);
		
		// if chose (delivered) update order delivery date
		if (deliveryOption.equals("delivered")) {
			String deliveryDate = UserInput.getDate("delivery date");
			order.setDeliveryDate(deliveryDate);
		}	
	}
	
	public static void updatePersonalInfo(Customer customer) {
		System.out.println("what would you like to update?");
		String[] options = {"phone number", "adress", "none"};
		String userSelection = UserInput.getStringFromOptions(options);
		
		if(userSelection.equals(options[0])) { // phone number
			String phoneNumber = UserInput.getPhoneNumber();
			customer.setPhoneNumber(phoneNumber);
		}
		
		else if (userSelection.equals(options[1])){ // adress
			String adress = UserInput.getAddress();
			customer.setAdress(adress);
		}
		
	}
	
	/**
	 * @param customer who's order it is
	 * @param restaurants array of available restaurants
	 * @return Order if created successfully otherwise null
	 */
	public static Order createNewOrder(Customer customer, Restaurant[] restaurants) {
		
		//print options
		for (int i = 0; i < restaurants.length; i++) {
			System.out.println(i + ". " + restaurants[i]);
		}
		
		// choose restaurant
		int restaurantIndex = UserInput.getIntFromRange(1, restaurants.length, "restaurant");
		Restaurant restaurant = restaurants[restaurantIndex];
		
		// get base amount
		double baseCost = UserInput.getDouble("base cost");
		
		// calculate price
		double price = restaurant.calculatePrice(baseCost);
		
		if (restaurant instanceof PremiumRestaurant) {
			if(price < ((PremiumRestaurant) restaurant).getMinOrderValue()) {				
				System.out.println("cost too low for order, must be more than " + ((PremiumRestaurant) restaurant).getMinOrderValue());
				return null;	
			}
		}
		
		// get date
		String date = UserInput.getDate("todays date");
		
		return new Order(customer.getCustomerCode(), restaurant, baseCost, price, date);
	}
	
	
	public static boolean notInAnArray(int code, Customer[] customerArray) {
		for (int i = 0; i < customerArray.length; i++) {
			if (customerArray[i] != null && customerArray[i].getCustomerCode() == code) {
				System.out.println("invalid field");
				return false;
			}
		}
		return true;
	}
	
	public static boolean notInAnArray(String code, RestAdmin[] RestAdminArry) {
		for (int i = 0; i < RestAdminArry.length; i++) {
			if (RestAdminArry[i] != null && RestAdminArry[i].getUsername().equals(code)) {
				System.out.println("invalid feald");
				return false;
			}
		}
		return true;
	}
	
	
	public static Customer addCustomer(Customer[] CustomerArry) {
		String name =  UserInput.getName("name");
		
		String famillyName = UserInput.getName("familly name");
		
		String adress = UserInput.getAddress();
		
		String phoneNumber = UserInput.getPhoneNumber();
		
		String email = UserInput.getEmail();
		
		double remainingCredit = UserInput.getDouble("remaining credit");
		return new Customer(name, famillyName, adress, phoneNumber, email, remainingCredit);
	}
		
		
		
	public static RestAdmin addRestAdmin(RestAdmin[] RestAdminArry) {
		String username = UserInput.getUsername();
	
		String restAdminName = UserInput.getName("resturnt admin name");
		
		String password = UserInput.getPassword();
		
		return new RestAdmin(restAdminName, username, password);
	}
		
		
	public static RestAdmin findRestAdmin(String username, RestAdmin[] restaurantAdmins) {
		for (int i = 0 ; i<restaurantAdmins.length ; i++) {
			if (restaurantAdmins[i] != null) {
				if (username.equals(restaurantAdmins[i].getUsername())) {
					return restaurantAdmins[i];
				}
			}
		}
		return null;
	}
	
	public static Restaurant findRestaurant(int restCode, Restaurant[] restaurants) {
		for (int i = 0 ; i<restaurants.length ; i++) {
			if (restaurants[i] != null) {
				if (restCode == restaurants[i].getRestaurantCode()) {
					return restaurants[i];
				}
			}
		}
		return null;
	}
	
	public static boolean assignRestAdminToRestaurant(RestAdmin[] restaurantAdmins, Restaurant[] restaurants) {
		RestAdmin restAdmin = findRestAdmin(UserInput.getUsername(), restaurantAdmins);
		Restaurant restaurant = findRestaurant(UserInput.getInt("restaurant code"), restaurants);
		if (restAdmin!= null && restaurant!=null) {
			restAdmin.addRestaurant(restaurant);
			return true;
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
		
		double deliveryFee = UserInput.getDouble(kitchenType);

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
	
	public static Rider findRider(String id, Rider[] riders) {
		if(!Validation.isId(id)) {
			return null;
		}
		
		for (Rider rider : riders) {
			if (rider != null && id.equals(rider.getId())) {
				return rider;
			}
		}
		
		return null;
	}
	
	public static Order findOrder(int orderCode, Order[] orders) {
		for (int i = 0 ; i<orders.length ; i++) {
			if (orders[i] != null) {
				if (orderCode == orders[i].getOrderCode()) {
					return orders[i];
				}
			}
		}
		return null;
	}
	
	public static boolean assignOrderToRider(Rider[] riders, Order[] orders) {
		Rider rider = findRider(UserInput.getId(), riders);
		Order order = findOrder(UserInput.getInt("order"), orders);
		if (rider != null && order != null && rider.getAvailable()){
			boolean added = rider.addOrder(order);
			if(added) {
				order.setDriverId(rider.getId());
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
	public static boolean RestAdminAddOrder(RestAdmin[] restaurantAdmins, Restaurant[] restaurants) {
		RestAdmin restAdmin = findRestAdmin(UserInput.getUsername(), restaurantAdmins);
		Restaurant restaurant = findRestaurant(UserInput.getInt("restaurant code"), restaurants);
		Restaurant restforAdmin = findRestaurant(restaurant.getRestaurantCode(), restAdmin.getRestaurants());
		if (restAdmin!= null && restforAdmin!=null) {
			return true;
		}
		System.out.println("the restaurant admins or the restaurant can not be found");
		return false;
	}
	
	public static Customer findCustomer(int customerCode, Customer[] customers) {
		for (int i = 0 ; i<customers.length ; i++) {
			if (customers[i] != null) {
				if (customerCode == customers[i].getCustomerCode()) {
					return customers[i];
				}
			}
		}
		return null;
	}
	
	public static Order createNewOrderByRestAdmin(RestAdmin restAdmin, Customer[] customers) {
		
		//print options
		for (int i = 0; i < restAdmin.getRestaurantCount(); i++) {
			System.out.println(i + ". " + restAdmin.getRestaurants()[i]);
		}
		
		// choose restaurant
		int restaurantIndex = UserInput.getIntFromRange(1, restAdmin.getRestaurantCount(), "restaurant");
		Restaurant restaurant = restAdmin.getRestaurants()[restaurantIndex];
		
		// choose customer code if it not exsist it breaks out of the func
		Customer customer = findCustomer(UserInput.getInt("customer code"), customers);
		if (customer == null) {
			return null;
		}
		
		// get base amount
		double baseCost = UserInput.getDouble("base cost");
		
		// calculate price
		double price = restaurant.calculatePrice(baseCost);
		
		if (restaurant instanceof PremiumRestaurant) {
			if(price < ((PremiumRestaurant) restaurant).getMinOrderValue()) {				
				System.out.println("cost too low for order, must be more than " + ((PremiumRestaurant) restaurant).getMinOrderValue());
				return null;	
			}
		}
		
		// get date
		String date = UserInput.getDate("todays date");
		
		return new Order(customer.getCustomerCode(), restaurant, baseCost, price, date);
	}
}