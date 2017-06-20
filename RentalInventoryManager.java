import java.io.*;
import java.util.*;

public class RentalInventoryManager {
	
	private final int AVAILABLE = 1, IN_USE = 2, BEING_SERVICED = 3;
	private int totalVehcilCount;
	private List<Vehicle> inUse, available, inService = new Vector<Vehicle>();
	private File file = new File(System.getProperty("user.home"), "RentalIventory.txt");
	private List<Vehicle>[] MasterList = new List[]{available, inUse, inService};
	
	//Properties is a subclass of Hashtable.
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
	
	public boolean isAvailable(String vin){
		for (Vehicle vehcile : available){
			if (vehcile.getVin().equals(vin)){
				return true;
			} 
		}
		return false;	
	}
	
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
