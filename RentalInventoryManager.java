import java.io.*;
import java.util.*;

public class RentalInventoryManager {
	//Change public to final as we do not want this to be accessed outside of the class since values or final
	private final int AVAILABLE = 1, IN_USE = 2, BEING_SERVICED = 3;
	
	//Camel casing for variables, leave underscores for constants
	private int totalVehcilCount;
	
	//Think about changing the vector to ArrayList as it is 20-30% faster. However vector is synchronized.
	private List<Vehicle> inUse, available, inService = new Vector<Vehicle>();
	private File file = new File(System.getProperty("user.home"), "RentalIventory.txt");
	
	//Most likely wrong, better way to create a list that contains the 3 list
	private List<Vehicle>[] MasterList = new List[]{available, inUse, inService};
	
	//Properties is a subclass of Hashtable. It is used to maintain lists of values in which the key is a 
	//String and the value is also a String.
	private Properties archive = new Properties();
	
	public RentalInventoryManager(){
		try {
			archive.load(new FileInputStream(file));
		}catch (IOException e){
			System.err.println(String.format("Error %s loading file %s from disk", e));
		}
		for (Object key : archive.keySet()){
			Vehicle vehicle = new Vehicle((String)key);
			int state = Integer.parseInt((String)archive.get(key));
			addRentalToInventory(vehicle, state);
		}
			
	}
	
	//Add the File not found throw exception.
	public void saveState() throws FileNotFoundException, IOException{
		for (int i = 0; i < MasterList.length; i++){
			for (Vehicle vehicle : MasterList[i]){
				archive.setProperty(vehicle.toString(), String.valueOf(i+1));	
			}
		}
		archive.store(new FileOutputStream(file), "RentalInventory");
	}
	
	//The state of the car is based on integers not strings so we need to update the switch case
	//to better reflect this. 1 is for available, 2 is for in use and 3 is for in service. 
	public void addRentalToInventory(Vehicle vehicle, int state){
		switch(state){
		case 1:
			available.add(vehicle);
		case 2:
			inUse.add(vehicle);
		case 3:
			inService.add(vehicle);
		}
	}
	
	public void removeRentalFromInventory(Vehicle vehicle){
		for(int i = 0; i < MasterList.length; i++){
			MasterList[i].remove(vehicle);
		}
	}
	
	public void changeState(Vehicle vehicle, int state){
		removeRentalFromInventory(vehicle);
		addRentalToInventory(vehicle, state);
	}
	
	public List getAllAvailable(){
		return available;
	}
	
	//Change list to ArrayList and also assign the uppercase to the variable type to capture it
	//Type is a private instance variable, created a getType method to access it
	public List getAvailableVehicleType(String type){
		if (available.isEmpty()){
			return null;
		}
		ArrayList<Vehicle> a = new ArrayList<Vehicle>();
		type = type.toUpperCase();
		for (Vehicle vehicle : available){
			if(type.equals(vehicle.getType())){
				a.add(vehicle);	
			}
		}
		return a;
	}
	
	//Look for a comparison of vin as .contains of a list looks for a vehicle object
	//If you just pass it a vin it will always come out to false
	public boolean isAvailable(String vin){
		for (Vehicle vehcile : available){
			if (vehcile.getVin().equals(vin)){
				return true;
			} 
		}
		return false;	
	}
	
	//Need to cast the integer division to double to then be captures by the double variable
	//Prior it was just integer division being stored in a variable with a double data type
	// Also fixed a closing parenthesis on the string for summary
	public String toString(){
		double p = (double)available.size() / (available.size() + inUse.size());
		String rate = String.format("%,.2f", p);
		String summary = new String("Rental Inventory (" + rate + "% utilization\n");
		String[] labels = new String[]{"Avaialble", "InUse", "InService"};
		for (int i = 0; i < MasterList.length; i++){
			summary += labels[i] + "\n";
			for (Vehicle vehicle : MasterList[i]){
				summary += "\t" + vehicle + "\n";
			}
		}
		return summary;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
