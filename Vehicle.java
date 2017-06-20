
public class Vehicle {
	//Change static to final because the types of cars will not change as they are constants
	//Another hit to this is that all the names are in caps which are typical for constants
	final String CAR = "CAR", TRUCK = "TRUCK", VAN = "VAN";
	
	//Change final to private. Type and vin were never initialized and since they are final they
	// can not be changed. They also should be lower case as they are not constants.
	private String type;

	private String vin;

	private String serializedForm;
	
	//Change package-private to public to allow other classes to use it
	// Package private is rarely a good choice and should typically be used with interfaces/enumerations
	public Vehicle(String vin, String type){
		this(new String[]{vin, type});
	}
	
	//Change package-private to public to allow other classes to use it
	public Vehicle(String stringRepresentation){
		this(stringRepresentation.split("#"));	
	}
	
	//Change private access-modifier to public so it can be instantiated outside the class
	public Vehicle(String[] values) {
		vin = values[0].toUpperCase();
		type = values[1];
		serializedForm = vin + "#" + type;
	}
	
	// Replaced & with && to allow short circuiting. If first statement is false don't evaluate second statement as it is already false
	// Replace == with equals method as you are comparing strings and not integers. 
	public boolean equals(Vehicle other){
		if(other != null && this.vin.equals(other.vin)){
			return true;
		} else {
			return false;
		}
	}
	
	//Instance variables are private so we need getters to return their values
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
