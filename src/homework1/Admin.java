package homework1;

public class Admin {
	protected String name;
	protected String username;
	protected String password;
	
	public Admin(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	public boolean login(String username, String password) {
		return username.equals(this.username) && password.equals(this.password);
	}
	
	public void menu(DeliverySystem DS) {
		// submenu of admin options	
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Admin) {
			Admin other = (Admin) obj;
			return other.username.equals(this.username);
		}
		return false;
	}
}
