package Utils;

import homework1.DeliverySystem;

public interface User {
	default void menu(DeliverySystem DS) {
		System.out.println("menu not implemented");
	};
	
	default void login(DeliverySystem DS) {
		System.out.println("menu not implemented");
	};
}
