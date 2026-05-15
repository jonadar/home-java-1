package homework1;

import java.util.Scanner;

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
		// TODO
		
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
	
	
	// TODO, cleanup
	public static boolean notInAnArray(int code, Customer[] customerArray) {
		for (int i = 0; i < customerArray.length; i++) {
			if (customerArray[i] != null && customerArray[i].getCustomerCode() == code) {
				System.out.println("invalid field");
				return false;
			}
		}
		return true;
	}
	
	// TODO, cleanup
	public static boolean notInAnArray(String code, RestAdmin[] RestAdminArry) {
		for (int i = 0; i < RestAdminArry.length; i++) {
			if (RestAdminArry[i] != null && RestAdminArry[i].getUsername().equals(code)) {
				System.out.println("invalid feald");
				return false;
			}
		}
		return true;
	}
	
	public static Customer addCustomer(Scanner scan, Customer[] CustomerArry) {
		String name;
		while (true) {
			if (scan.hasNextLine()) {
				String item = scan.nextLine();
				if (!Validation.isEmptyString(item) && Validation.isOnlyChars(item)) {
					name = item;
					System.out.println("the name is " + name);
					break;
				}
				scan.next();
			}
			else { 
				System.out.println("not a valid name");
			}
		}
		
		String famillyName;
		while (true) {
			if (scan.hasNextLine()) {
				String item = scan.nextLine();
				if (!Validation.isEmptyString(item) && Validation.isOnlyChars(item)) {
					famillyName = item;
					System.out.println("the familly name is " + famillyName);
					break;
				}
			} else {
				System.out.println("not a valid familly name");
			}
		}
		String adress = UserInput.getAddress();
		
		String phoneNumber;
		while(true) {
			System.out.println("enter phone number");
			if(scan.hasNextLine()) {
				String item = scan.nextLine();
				if (!Validation.isEmptyString(item) && Validation.isOnlyDigits(item) && item.length() == 10) {
					phoneNumber = item;
					break;
				} else { 
					System.out.println("invalid phone number");
				}
			}
		}
		
		String email;
		while(true) {
			System.out.println("enter email");
			if(scan.hasNextLine()) {
				String item = scan.nextLine();
				if (Validation.isEmail(item)) {
					email = item;
					break;
				} else {
					System.out.println("invalid email");
				}
			}
		}
		
		double remainingCredit;
		while(true) {
			System.out.println("enter remaining credit");
			if(scan.hasNextDouble()) {
				Double item = scan.nextDouble();
				if (item > -1) {
					remainingCredit = item;
						break;
				} else {
					System.out.println("invalid remaining credit");
				}
			} else { 
				System.out.println("invalid remaining credit");
				scan.next();
			}
		}// לוודא מה לעשות לקוד ולריימנינג קרדיט
		return new Customer(name, famillyName, adress, phoneNumber, email, remainingCredit);
	}
		
		
		
	public static RestAdmin addRestAdmin(Scanner scan, RestAdmin[] RestAdminArry) {
		String username; // זה מחרוזת לתקן
		while(true) {
			System.out.println("enter resturnt admin username");
			if(scan.hasNextLine()) {
				String item = scan.nextLine();
				if (notInAnArray(item, RestAdminArry)) {
					username = item;
					break;
				}
			} else {
				System.out.println("invaled feald");
			}
		}
	
		String restAdminName;
		while(true) {
			System.out.println("enter resturnt admin name");
			if(scan.hasNextLine()) {
				String item = scan.nextLine();
				if (!Validation.isEmptyString(item) && Validation.isOnlyChars(item)) {
					restAdminName = item;
					break;
				} else {
					System.out.println("invaled feald");
					scan.nextInt();
				}
			}
				
		}
		
		String password;
		while(true) {
			System.out.println("enter resturnt admin username");
			if(scan.hasNextLine()) {
				String item = scan.nextLine();
				if (!Validation.isEmptyString(item) && Validation.isOnlyNumbersAndChars(item)) {
					password = item;
					break;
				} else {
					System.out.println("invaled feald");
					scan.next();
				}
			}
		}
		
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
	
	public void chooseRest(int num) {
		int restaurant = 0;
		switch (restaurant) {
		case 1:
				// הוספת מסעדה רגילה
			break;
		case 2:
			//	 הוספת מסעדה מהירה
			break;
		case 3:
			// הוספת מסעדת יוקרה
			break;
		default:
			System.out.println("invalid number");
		}
	}
	
	public static Restaurant addRestaurant() {
		String restName = UserInput.getName("resaurant name");
		
		String kitchenType = UserInput.getName("kitchen type");
		
		double rating = UserInput.getDoubleFromRange(0, 10, "rating");

		boolean isOpen = UserInput.getBoolean("is restaurant open?");
		
		double deliveryFee = UserInput.getDouble(kitchenType);

		return new Restaurant(restName, kitchenType, rating, isOpen, deliveryFee);
	}
	
	public static PremiumRestaurant addPremiumRestaurant(Scanner scan) {
		Restaurant bacicRest = addRestaurant();
		double minOrderValue;
		while(true) {
			if(scan.hasNextDouble()) {
				Double item = scan.nextDouble();
				if (item!=null) {
					minOrderValue = item;
					scan.nextLine();
					System.out.println("the restaurant rating is " + minOrderValue);
					break;
				}
			} else { 
				System.out.println("not a valid restaurant minimum order value");
				scan.next();
			}
		}
		double orderFeePercentage;
		while(true) {
			if(scan.hasNextDouble()) {
				Double item = scan.nextDouble();
				if (item!=null) {
					orderFeePercentage = item;
					scan.nextLine();
					System.out.println("the restaurant rating is " + orderFeePercentage);
					break;
				}
			} else { 
				System.out.println("not a valid restaurant order fee percentage");
				scan.next();
			}
		}

		return new PremiumRestaurant(bacicRest.getName(), bacicRest.getKitchenType(), bacicRest.getRating(), bacicRest.isOpen(), bacicRest.getDeliveryFee(), minOrderValue, orderFeePercentage);
	}
	
	public static PremiumRestaurant addFastFoodRestaurant(Scanner scan) {
		Restaurant bacicRest = addRestaurant();
		double averageCookTime;
		while(true) {
			if(scan.hasNextDouble()) {
				Double item = scan.nextDouble();
				if (item!=null) {
					averageCookTime = item;
					scan.nextLine();
					System.out.println("the restaurant rating is " + averageCookTime);
					break;
				}
			} else { 
				System.out.println("not a valid restaurant average cook time");
				scan.next();
			}
		}
		
		double fastDeliveryFee;
		while(true) {
			if(scan.hasNextDouble()) {
				Double item = scan.nextDouble();
				if (item!=null) {
					fastDeliveryFee = item;
					scan.nextLine();
					System.out.println("the restaurant rating is " + fastDeliveryFee);
					break;
				}
			} else { 
				System.out.println("not a valid restaurant delivery fee");
				scan.next();
			}
		}
		return new PremiumRestaurant(bacicRest.getName(), bacicRest.getKitchenType(), bacicRest.getRating(), bacicRest.isOpen(), bacicRest.getDeliveryFee(), averageCookTime, fastDeliveryFee);
	}
	
	public static Rider addRider(Scanner scan) {
		String id = UserInput.getId();
	
		String fullName;
		while(true) {
			if(scan.hasNextLine()) {
				String item = scan.nextLine();
				if (!Validation.isEmptyString(item) && Validation.isOnlyChars(item)) {
					fullName = item;
					System.out.println("the kitchen type is " + fullName);
					break;
				}
			} else { 
				System.out.println("not a valid full name");
			}
		}
		String phoneNumber;
		while(true) {
			System.out.println("enter phone number");
			if(scan.hasNextLine()) {
				String item = scan.nextLine();
				if (!Validation.isEmptyString(item) && Validation.isOnlyDigits(item) && item.length() == 10) {
					phoneNumber = item;
					break;
				} else { 
					System.out.println("invalid phone number");
				}
			}
		}
		String vehicle;
		while(true) {
			if(scan.hasNextLine()) {
				String item = scan.nextLine();
				if (!Validation.isEmptyString(item) && Validation.isOnlyChars(item)) {
					vehicle = item;
					System.out.println("the kitchen type is " + vehicle);
					break;
				}
			} else { 
				System.out.println("not a valid vehicle");
			}
		}
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
		if (rider != null && order !=null && rider.getAvailable()){
			order.setDriverId(rider.getId());
			return true;
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
		
		// calculate price`
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