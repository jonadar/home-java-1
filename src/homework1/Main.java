package homework1;

import Utils.UserInput;

public class Main {
	public static void main(String[] args) {
		DeliverySystem DS = new DeliverySystem();

		// 5 riders
		DS.addRider(new Rider("1242154","Jhon man", "126591", "car", true));
		DS.addRider(new Rider("163262","miriam", "126591", "car", false));
		DS.addRider(new Rider("21252152","eden", "126591", "skateboard", true));
		DS.addRider(new Rider("16261242","gregor", "126591", "car", true));
		DS.addRider(new Rider("5126161","michelle", "126591", "corkinet", false));
		
		// 10 customers
		DS.addCustomer(new Customer("Jonathan", "Mil", "Modiin 1 green place 4415", "3981292512", "cafsaf@gmacom",0));
		DS.addCustomer(new Customer("Moulda", "lol", "Modiin 2 green place 4415", "5981292512", "safsaf@gmail", 200));
		DS.addCustomer(new Customer("Jack", "Popler", "Modiin 3 green place 4415", "7981292512", "ra521af@gmail", 0));
		DS.addCustomer(new Customer("iris", "cook", "Modiin 4 green place 4415", "2981262512", "eafssfaf@gmail", 20));
		DS.addCustomer(new Customer("jalta", "katz", "Modiin 5 fajksf 4415", "2981292542", "v2af@gmail",1));
		DS.addCustomer(new Customer("david", "haddad", "Modiin 6 green place 4415", "2588292312", "jafsaf@gmail",0));
		DS.addCustomer(new Customer("ron", "ald", "Modiin 7 green place 4415", "2981232562", "xafsaf@gmaicom",0));
		DS.addCustomer(new Customer("bob", "dirichle", "Modiin 8 laf kkfwao 4415", "2983792512", "jafsykjaf@gmaicom",0));
		DS.addCustomer(new Customer("greg", "leibnitz", "Modiin 9 green place 4415", "2951232512", "uabfsaf@gmaom",0));
		DS.addCustomer(new Customer("some", "guy", "Modiin 10 green place 4415", "2981294512", "oyaftsaf@gmaim",0));
		
		//10 restaurants of each type
		DS.addRestaurant(new Restaurant("the big fat whale", "big", 5, true, 10));
		DS.addRestaurant(new Restaurant("flying dutch people", "big", 5, true, 10));
		DS.addRestaurant(new Restaurant("kuu & kang", "small", 5, true, 10));
		DS.addRestaurant(new Restaurant("the big fat whale", "big", 5, true, 10));
		DS.addRestaurant(new Restaurant("faxxxutch people", "big", 5, true, 10));
		DS.addRestaurant(new Restaurant("kvasnt & kang", "small", 5, true, 10));
		DS.addRestaurant(new Restaurant("fasf big fat whale", "big", 5, true, 10));
		DS.addRestaurant(new Restaurant("tttlying dutch people", "big", 5, true, 10));
		DS.addRestaurant(new Restaurant("yyklint & kang", "small", 5, true, 10));
		DS.addRestaurant(new Restaurant("xxlint & kang", "small", 5, true, 10));
		
		DS.addRestaurant(new FastFoodRestaurant("bob's burgers", "big", 5, true, 10, 15, 20));
		DS.addRestaurant(new FastFoodRestaurant("greg's ultra delux shwarma", "big", 5, true, 10, 3, 40));
		DS.addRestaurant(new FastFoodRestaurant("krusty burger", "big", 5, true, 10, 10, 23));
		DS.addRestaurant(new FastFoodRestaurant("kaflsflas", "big", 7, true, 10, 15, 20));
		DS.addRestaurant(new FastFoodRestaurant("blao xis", "big", 4, true, 10, 3, 40));
		DS.addRestaurant(new FastFoodRestaurant("dinmpa", "big", 5, true, 10, 10, 23));
		DS.addRestaurant(new FastFoodRestaurant("doom doom dum dum", "big", 2, true, 10, 15, 20));
		DS.addRestaurant(new FastFoodRestaurant("lary sucks", "big", 5, true, 10, 3, 40));
		DS.addRestaurant(new FastFoodRestaurant("michelle enjoyers", "big", 5, true, 10, 10, 23));
		DS.addRestaurant(new FastFoodRestaurant("big mac", "big", 5, true, 10, 10, 23));
		
		DS.addRestaurant(new PremiumRestaurant("cavern on the green", "big", 5, true, 10, 120, 3));
		DS.addRestaurant(new PremiumRestaurant("little garden", "big", 5, true, 10, 150, 10));
		DS.addRestaurant(new PremiumRestaurant("canary blue", "big", 5, true, 10, 200, 5));
		DS.addRestaurant(new PremiumRestaurant("sparrow", "big", 5, true, 10, 100, 10));
		DS.addRestaurant(new PremiumRestaurant("creme pao", "big", 5, true, 10, 120, 3));
		DS.addRestaurant(new PremiumRestaurant("poxter", "big", 5, true, 10, 150, 10));
		DS.addRestaurant(new PremiumRestaurant("noding", "big", 5, true, 10, 200, 5));
		DS.addRestaurant(new PremiumRestaurant("bill and jills dill grill", "big", 5, true, 10, 100, 10));
		DS.addRestaurant(new PremiumRestaurant("cda", "big", 5, true, 10, 200, 5));
		DS.addRestaurant(new PremiumRestaurant("BBA", "big", 5, true, 10, 100, 10));
		
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
					String adminUsername = UserInput.getUsername();
					String adminPassword = UserInput.getPassword();
					// login as admin
					if(admin.login(adminUsername, adminPassword)) {
						System.out.println("you are admin");
						admin.menu(DS);
					}
					
					break;
				case(2):
					// restAdmin login using password and username
					String username = UserInput.getUsername();
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
					String id = UserInput.getId();
				
					Rider rider = null;
					for (Rider r: DS.getRiders()) {
						if(r.getId().equals(id)) {
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
