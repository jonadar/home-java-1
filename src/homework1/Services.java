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
			String adress = UserInput.getAdress();
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
	
	
	// TODO, cleanup
	public static boolean notInAnArry(int code, Customer[] customerArry) {
		for (int i = 0; i < customerArry.length; i++) {
			if (customerArry[i] != null && customerArry[i].getCustomerCode() == code) {
				System.out.println("invalid feald");
				return false;
			}
		}
		return true;
	}
	
	// TODO, cleanup
	public static boolean notInAnArry(String code, RestAdmin[] RestAdminArry) {
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
		String adress = UserInput.getAdress();
		
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
				if (notInAnArry(item, RestAdminArry)) {
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
		RestAdmin restAdmin = findRestAdmin(UserInput.getUserName(), restaurantAdmins);
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
	
	public static Restaurant addRestaurant(Scanner scan) {
		String restName;
		while(true) {
			if(scan.hasNextLine()) {
				String item = scan.nextLine();
				if (!Validation.isEmptyString(item) && Validation.isOnlyChars(item)) {
					restName = item;
					System.out.println("the restaurant name is " + restName);
					break;
				}
			}else { 
				System.out.println("not a valid restaurant name");	
			}
		}
		String kitchenType;
		while(true) {
			if(scan.hasNextLine()) {
				String item = scan.nextLine();
				if (!Validation.isEmptyString(item) && Validation.isOnlyChars(item)) {
					kitchenType = item;
					System.out.println("the kitchen type is " + kitchenType);
					break;
				}
			} else { 
				System.out.println("not a valid kitchen type");
			}
		}
		
		double rating;
		while(true) {
			if(scan.hasNextDouble()) {
				Double item = scan.nextDouble();
				if (item!=null) {
					rating = item;
					scan.nextLine();
					System.out.println("the restaurant rating is " + rating);
					break;
				}
			} else { 
				System.out.println("not a valid restaurant rating");
				scan.next();
			}
		}
		boolean isOpen = UserInput.getBoolean("is restaurant open");
		
		double deliveryFee;
		while(true) {
			if(scan.hasNextDouble()) {
				Double item = scan.nextDouble();
				if (item != null) {
					deliveryFee = item;
					scan.nextLine();
					System.out.println("the restaurant delivery fee is " + deliveryFee);
					break;
				}
			} else { 
				System.out.println("not a valid restaurant delivery fee");
				scan.next();
			}
		}
		
		return new Restaurant(restName, kitchenType, rating, isOpen, deliveryFee);
	}
}