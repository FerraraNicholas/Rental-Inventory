
public class Vehicle {
	final String CAR = "CAR", TRUCK = "TRUCK", VAN = "VAN";
	private String type;
	private String vin;
	private String serializedForm;
	
	public Vehicle(String vin, String type){
		this(new String[]{vin, type});
	}
	
	public Vehicle(String stringRepresentation){
		this(stringRepresentation.split("#"));	
	}
	
	public Vehicle(String[] values) {
		vin = values[0].toUpperCase();
		type = values[1];
		serializedForm = vin + "#" + type;
	}
	
	public boolean equals(Vehicle other){
		if(other != null && this.vin.equals(other.vin)){
			return true;
		} else {
			return false;
		}
	}
	
	public String getType(){
		return type;
	}
	
	public String getVin(){
		return vin;
	}
	
	public String toString(){
		return serializedForm;
	}

}
