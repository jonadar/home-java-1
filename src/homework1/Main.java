package homework1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		DeliverySystem DS = new DeliverySystem();
		
		// 5 riders
		DS.addRider(new Rider());
		DS.addRider(new Rider());
		DS.addRider(new Rider());
		DS.addRider(new Rider());
		DS.addRider(new Rider());
		
		// 10 customers
		DS.addCustomer(new Customer("Jonathan", "Mil", "1 green place", "053-2981-2925-12", "jafsaf@gmail.com"));
		DS.addCustomer(new Customer("Moulda", "lol", "2 green place", "053-2981-2925-12", "jafsaf@gmail.com"));
		DS.addCustomer(new Customer("Jack", "Popler", "3 green place", "053-2981-2925-12", "jafsaf@gmail.com"));
		DS.addCustomer(new Customer("iris", "cook", "4 green place", "053-2981-2925-12", "jafsaf@gmail.com"));
		DS.addCustomer(new Customer("jalta", "katz", "5 fajksf", "053-2981-2925-12", "jafsaf@gmail.com"));
		DS.addCustomer(new Customer("david", "haddad", "6 green place", "053-2981-2925-12", "jafsaf@gmail.com"));
		DS.addCustomer(new Customer("ron", "ald", "7 green place", "053-2981-2925-12", "jafsaf@gmail.com"));
		DS.addCustomer(new Customer("bob", "dirichle", "8 laf kkfwao", "053-2981-2925-12", "jafsaf@gmail.com"));
		DS.addCustomer(new Customer("greg", "leibnitz", "9 green place", "053-2981-2925-12", "jafsaf@gmail.com"));
		DS.addCustomer(new Customer("some", "guy", "10 green place", "053-2981-2925-12", "jafsaf@gmail.com"));
		
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
		DS.addRestaurantAdmin(new RestAdmin("jon", "secretpass&52", null, null, 0));
		DS.addRestaurantAdmin(new RestAdmin("eden", "IH8Michelle", null, null, 0));
		DS.addRestaurantAdmin(new RestAdmin("bill", "sec51pass&52", null, null, 0));
		
		Admin admin = new Admin("Steve", "admin", "12345");
		
		// תפריט
		while(true) {
			System.out.println("1. admin login");
			System.out.println("2. restaurant admin login");
			System.out.println("3. rider login");
			System.out.println("4. customer login");
			int option = input.nextInt();
//			if (input.hasNextInt()) {
//				// has int to scan
//			} else {
//				// invalid input, ask again for valid input
//			}
			
			// validate option
			
			switch(option) {
				case(1):
					System.out.println("you are admin");
					while(true) {
						System.out.println("1. add customer");
						System.out.println("2. add restaurant admin");
						System.out.println("3. add restaurant admin to restaurant");
						System.out.println("4. add restaurant");
						System.out.println("5. add rider");
						System.out.println("6. add rider to order");
						int adminOption = input.nextInt();
						if(adminOption == 7) break;
						// validate option
					}
					// inner loop for admin options
					break;
				case(2):
					System.out.println("you are restaurant admin");
					while(true) {
						System.out.println("1. add customer");
						System.out.println("2. add rider");
						System.out.println("3. add rider to order");
						int restAdminOption = input.nextInt();
						if(restAdminOption == 4) break;
						// validate option
					}
					break;
				case(3):
					System.out.println("you are rider");
					while(true) {
						System.out.println("1. add customer");
						System.out.println("2. add restaurant admin");
						System.out.println("3. add restaurant admin to restaurant");
						System.out.println("4. add restaurant");
						System.out.println("5. add rider");
						System.out.println("6. add rider to order");
						int riderOption = input.nextInt();
						if(riderOption == 7) break;
						// validate option
					}
					break;
				case(4):
					System.out.println("you are customer");
					while(true) {
						System.out.println("1. make order");
						System.out.println("2. get order details");
						int customerOption = input.nextInt();
						if(customerOption == 7) break;
						// validate option
					}
					break;
				default:
					break;
			}
		}
		
//		input.close();
		
	}
}
