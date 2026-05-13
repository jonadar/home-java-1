package homework1;

import java.util.Iterator;

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
			order.setOrderDate("2/2/5"); //TODO user input
		}
	}
	
	public static void displayAllOrders(Rider rider) {
		Order[] orders = rider.getOrders();
		
		if(orders == null || orders.length == 0) {
			System.out.println("no orders to display");
			return;
		}
		
		System.out.println("your orders are: ");
		for (int i = 0; i < orders.length; i++) {
			System.out.println((i+1) + ". " + orders[i]);
		}
	}
	
	public static void displayAllOrders(Customer customer) {}
	
	public static void createNewOrder() {}
	
	public static void updatePersonalInfo() {}
	
	public static void displayRestaurantDetails() {}
	
	
}
