package homework1;

import java.util.Scanner;

import Utils.UserInput;
import Utils.Validation;

public class Services {
	
	public static void main(String[] args) {
		Rider r = new Rider("creig", 251924, "car");

		Restaurant res = new Restaurant("the big fat whale", "big", 5, true, 10);
		Order[] o = {
				new Order(2, res, 0, null, 0, null),
				new Order(2, res, 0, null, 0, null),
				new Order(2, res, 0, null, 0, null)
		};
		r.setOrders(o);
		
		updateOrderStatus(r);
	}

	
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
		// TODO
		
	}
	
	public static void createNewOrder(Customer customer) {
		// TODO
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
	
	
	public Customer addCustomer(Scanner scan, Customer[] CustomerArry) {
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
				scan.next();
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
				scan.next();
			}
		}
		String adress;
		//להוסיף ולידציה לכתובת
		
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
		
		
		
		public RestAdmin addRestAdmin(Scanner scan, RestAdmin[] RestAdminArry) {
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
	
	
}
