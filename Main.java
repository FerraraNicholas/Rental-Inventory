import java.io.File;
import java.util.List;
import java.util.Vector;

public class Main {


	public static void main(String[] args) {
		//List<Vehicle> available = null, inUse = null, inService = new Vector<Vehicle>();
		//List<Vehicle>[] MasterList = new List[]{available, inUse, inService};
		
		List<Vehicle> available = new Vector<Vehicle>();
		List<Vehicle> inUse = new Vector<Vehicle>();
		List<Vehicle> inService = new Vector<Vehicle>();
		
		Vehicle v1 = new Vehicle("EEEEE", "CAR");
		Vehicle v2 = new Vehicle("EEEFF", "CAR");
		Vehicle v3 = new Vehicle("EEEGG", "CAR");
		
		Vehicle v4 = new Vehicle("EEEHH", "CAR");
		Vehicle v5 = new Vehicle("EEEII", "CAR");
		Vehicle v6 = new Vehicle("EEEJJ", "CAR");
		
		Vehicle v7 = new Vehicle("EEEKK", "CAR");
		Vehicle v8 = new Vehicle("EEELL", "CAR");
		Vehicle v9 = new Vehicle("EEEMM", "CAR");
		
		available.add(v1);
		available.add(v2);
		available.add(v3);
		
		inUse.add(v4);
		inUse.add(v5);
		inUse.add(v6);
		
		inService.add(v7);
		inService.add(v8);
		inService.add(v9);
		List<Vehicle>[] MasterList = new List[]{available, inUse, inService};	
	}

}
