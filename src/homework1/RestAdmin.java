package homework1;

public class RestAdmin extends Admin{
	private Restaurant[] restaurants;
	private int restaurantCount;
	
	public RestAdmin(String name, String username, String password, Restaurant[] restaurants, int restaurantCount) {
		super(name, username, password);
		this.restaurants = restaurants;
		this.restaurantCount = restaurantCount;
	}
}
