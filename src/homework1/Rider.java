package homework1;

public class Rider {
	int id;
	String fullName;
	int phoneNumber;
	String vehicle;
	boolean isAvailable;
	Order[] orders;
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Rider) {
			Rider other = (Rider) obj;
			return other.id == this.id;
		}
		return false;
	}
}
