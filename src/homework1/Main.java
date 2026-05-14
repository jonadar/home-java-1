package homework1;

import java.util.Scanner;

import Utils.UserInput;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		DeliverySystem DS = new DeliverySystem();

		// 5 riders
		DS.addRider(new Rider("Jhon man", 126591, "car"));
		DS.addRider(new Rider("miriam", 126591, "car"));
		DS.addRider(new Rider("eden", 126591, "skateboard"));
		DS.addRider(new Rider("gregor", 126591, "car"));
		DS.addRider(new Rider("michelle", 126591, "corkinet"));
		
		// 10 customers
		DS.addCustomer(new Customer("Jonathan", "Mil", "1 green place", "053-2981-2925-12", "cafsaf@gmail.com",0));
		DS.addCustomer(new Customer("Moulda", "lol", "2 green place", "053-2981-2925-12", "safsaf@gmail.com", 200));
		DS.addCustomer(new Customer("Jack", "Popler", "3 green place", "053-2981-2925-12", "ra521af@gmail.com", 0));
		DS.addCustomer(new Customer("iris", "cook", "4 green place", "053-2981-2925-12", "eafssfaf@gmail.com", 20));
		DS.addCustomer(new Customer("jalta", "katz", "5 fajksf", "053-2981-2925-12", "v2af@gmail.com",1));
		DS.addCustomer(new Customer("david", "haddad", "6 green place", "053-2981-2925-12", "jafsaf@gmail.com",0));
		DS.addCustomer(new Customer("ron", "ald", "7 green place", "053-2981-2925-12", "xafsaf@gmail.com",0));
		DS.addCustomer(new Customer("bob", "dirichle", "8 laf kkfwao", "053-2981-2925-12", "jafsykjaf@gmail.com",0));
		DS.addCustomer(new Customer("greg", "leibnitz", "9 green place", "053-2981-2925-12", "uabfsaf@gmail.com",0));
		DS.addCustomer(new Customer("some", "guy", "10 green place", "053-2981-2925-12", "oyaftsaf@gmail.com",0));
		
		//10 restaurants
		DS.addRetaurant(new Restaurant("the big fat whale", "big", 5, true, 10));
		DS.addRetaurant(new Restaurant("flying dutch people", "big", 5, true, 10));
		DS.addRetaurant(new Restaurant("klint & kang", "small", 5, true, 10));
		
		DS.addRetaurant(new FastFoodRestaurant("bob's burgers", "big", 5, true, 10, 15, 20));
		DS.addRetaurant(new FastFoodRestaurant("greg's ultra delux shwarma", "big", 5, true, 10, 3, 40));
		DS.addRetaurant(new FastFoodRestaurant("krusty burger", "big", 5, true, 10, 10, 23));
		
		DS.addRetaurant(new PremiumRestaurant("cavern on the green", "big", 5, true, 10, 120, 3));
		DS.addRetaurant(new PremiumRestaurant("little garden", "big", 5, true, 10, 150, 10));
		DS.addRetaurant(new PremiumRestaurant("canary blue", "big", 5, true, 10, 200, 5));
		DS.addRetaurant(new PremiumRestaurant("sparrow", "big", 5, true, 10, 100, 10));
		
		//3 restaurant admins
		DS.addRestaurantAdmin(new RestAdmin("jon", "supfax", "secretpass52"));
		DS.addRestaurantAdmin(new RestAdmin("eden", "gasca", "IH8Michelle"));
		DS.addRestaurantAdmin(new RestAdmin("bill", "lapdaz", "sec51pass52"));
		
		Admin admin = new Admin("Steve", "admin", "12345");
		
		// תפריט
		while(true) {
			System.out.println("1. admin login");
			System.out.println("2. restaurant admin login");
			System.out.println("3. rider login");
			System.out.println("4. customer login");
			
			int option = UserInput.getIntFromRange(1, 4, "option");
			System.out.println(option);
			
			switch(option) {
				case(1):
					String adminUsername = UserInput.getUserName();
					String adminPassword = UserInput.getPassword();
					// login as admin
					if(admin.login(adminUsername, adminPassword)) {
						System.out.println("you are admin");
						admin.menu(DS);
					}
					
					break;
				case(2):
					// restAdmin login using password and username
					String username = UserInput.getUserName();
					String password = UserInput.getPassword();
				
					RestAdmin restaurantAdmin = null;
					for (RestAdmin ra: DS.getRestaurantAdmins()) {
						if(ra.getUsername().equals(username)) {
							restaurantAdmin = ra;
							break;
						}
					}
					
					if(restaurantAdmin == null) {
						System.out.println("restaurant admin not found.");
						break;
					}
					
					
					if(restaurantAdmin.login(username, password)) {
						System.out.println("you are restaurant admin");
						restaurantAdmin.menu(DS);
					}
					
					break;
				case(3):
					// rider login using id
					int id = UserInput.getInt("rider id");
				
					Rider rider = null;
					for (Rider r: DS.getRiders()) {
						if(r.getId() == id) {
							rider = r;
							break;
						}
					}
					
					if(rider == null) {
						System.out.println("rider not found.");
						break;
					}
					
					rider.menu(DS);
					
					break;
				case(4):
					// customer login with customer code
					int code = UserInput.getInt("customer code");
				
					Customer customer = null;
					for (Customer c: DS.getCustomers()) {
						if(c.getCustomerCode() == code) {
							customer = c;
							break;
						}
					}
					
					if(customer == null) {
						System.out.println("customer not found.");
						break;
					}
					
					customer.menu(DS);
				
					break;
				default:
					break;
			}
		}
		
//		input.close();
		
	}
}
