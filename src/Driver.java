import java.util.ArrayList;
import java.util.Scanner;

//import static java.lang.Integer.parseInt;

public class Driver {

    // instance variables (add more as needed)
    private static ArrayList<Ship> shipList = new ArrayList<Ship>();
    private static ArrayList<Cruise> cruiseList = new ArrayList<Cruise>();
    private static ArrayList<Passenger> passengerList = new ArrayList<Passenger>();


    public static void main(String[] args) {

        initializeShipList();       // initial ships
        initializeCruiseList();     // initial cruises
        initializePassengerList();  // initial passengers
        
        //Start scanning for input
        Scanner scnr = new Scanner(System.in);
      
        
        //Print Menu and Process input.q
        displayMenu();
        String menuSelection = scnr.nextLine();
        while(!menuSelection.equals("x")) {
        	
        	if(menuSelection.equals("1")) {
        		addShip();
        	}else if(menuSelection.equals("2")) {
        		editShip();
        	}else if(menuSelection.equals("3")) {
        		addCruise();
        	}else if(menuSelection.equals("4")) {
        		editCruise();
        	}else if(menuSelection.equals("5")) {
        		addPassenger();
        	}else if(menuSelection.equals("6")) {
        		editPassenger();
        	}else if(menuSelection.equals("A")) {
        		printShipList("name");
        	}else if(menuSelection.equals("B")) {
        		printShipList("active");
        	}else if(menuSelection.equals("C")) {
        		printShipList("full");
        	}else if(menuSelection.equals("D")) {
        		printCruiseList("list");
        	}else if(menuSelection.equals("E")) {
        		printCruiseList("details");
        	}else if(menuSelection.equals("F")) {
        		printPassengerList();
        	}else if(menuSelection.equals("M")) {
        		displayMenu();
        	}else {
        		//Print an error for invalid input, but let them try again. 
        		System.out.println("Error: Invalid option. Please try again, press 'M' to display the menu again, or 'x' to Exit.");
        		menuSelection = scnr.nextLine();
        		continue;
        	}
    		System.out.println("\nPress Enter to return to menu.");
            scnr.nextLine();
            displayMenu();
        	menuSelection = scnr.nextLine();
        }
        
        System.out.println("Goodbye");

        scnr.close();
    }

    // hardcoded ship data for testing
    // Initialize ship list
    public static void initializeShipList() {
        add("Candy Cane", 20, 40, 10, 60, true);
        add("Peppermint Stick", 10, 20, 5, 40, true);
        add("Bon Bon", 12, 18, 2, 24, false);
        add("Candy Corn", 12, 18, 2, 24, false);
    }

    // hardcoded cruise data for testing
    // Initialize cruise list
    public static void initializeCruiseList() {
        Cruise newCruise = new Cruise("Southern Swirl", "Candy Cane", "Miami", "Cuba", "Miami");
        cruiseList.add(newCruise);
    }

    // hardcoded cruise data for testing
    // Initialize passenger list
    public static void initializePassengerList() {
        Passenger newPassenger1 = new Passenger("Neo Anderson", "Southern Swirl", "STE");
        passengerList.add(newPassenger1);

        Passenger newPassenger2 = new Passenger("Trinity", "Southern Swirl", "STE");
        passengerList.add(newPassenger2);

        Passenger newPassenger3 = new Passenger("Morpheus", "Southern Swirl", "BAL");
        passengerList.add(newPassenger3);
    }

    // custom method to add ships to the shipList ArrayList
    public static void add(String tName, int tBalcony, int tOceanView,
                           int tSuite, int tInterior, boolean tInService) {
        Ship newShip = new Ship(tName, tBalcony, tOceanView, tSuite, tInterior, tInService);
        shipList.add(newShip);
    }


    public static void printShipList(String listType) {
        // printShipList() method prints list of ships from the
        // shipList ArrayList. There are three different outputs
        // based on the listType String parameter:
        // name - prints a list of ship names only
        // active - prints a list of ship names that are "in service"
        // full - prints tabbed data on all ships

        if (shipList.size() < 1) {
            System.out.println("\nThere are no ships to print.");
            return;
        }
        if (listType == "name") {
            System.out.println("\n\nSHIP LIST - Name");
            for (int i = 0; i < shipList.size(); i++) {
            	int n = i + 1;
                System.out.println("Ship "+ n + ": " + shipList.get(i));
            }
        } else if (listType == "active") {
            System.out.println("\n\nSHIP LIST - Active");
            
            //Set this variable to false now
            boolean shipsActive = false;
            
            //Sort through each ship.
            for (Ship eachShip: shipList) {
            	//Check if it's active.
            	if(eachShip.getInService() == true) {
            		//Print it out.
            		System.out.println(eachShip);
            		//Set shipsActive to true
            		shipsActive = true;
            	}
            }
            
            //If shipsActive is false, error.
            if(shipsActive == false) {
            	System.out.println("There are no active ships");
            }


        } else if (listType == "full") {
            System.out.println("\n\nSHIP LIST - Full");
            System.out.println("--------------------------------------------------------");
            System.out.println("                    Number of Rooms     \tIn");
            System.out.print("SHIP NAME           Bal\tOV \tSte\tInt     Service");
            System.out.println("\n--------------------------------------------------------");
            for (Ship eachShip: shipList)
                eachShip.printShipData();

        } else {
            System.out.println("\n\nError: List type not defined.");
        }
    }

    public static void printCruiseList(String listType) {
        if (cruiseList.size() < 1) {
            System.out.println("\nThere are no cruises to print.");
            return;
        }
        if (listType == "list") {
            System.out.println("\n\nCRUISE LIST");
            for (int i=0; i < cruiseList.size(); i++) {
            	int n = i + 1;
                System.out.println("Cruise "+ n + ": " +cruiseList.get(i));
            }
        } else if (listType == "details") {
            System.out.println("\n\nCRUISE LIST - Details");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("                                      |----------------------PORTS-----------------------|");
            System.out.print("CRUISE NAME         SHIP NAME           DEPARTURE           DESTINATION         RETURN");
            System.out.println("\n-----------------------------------------------------------------------------------------");
            for (Cruise eachCruise: cruiseList)
                eachCruise.printCruiseDetails();
        } else
            System.out.println("\n\nError: List type not defined.");
    }

    public static void printPassengerList() {
        if (passengerList.size() < 1) {
            System.out.println("\nThere are no passengers to print.");
            return;
        }
        System.out.println("\n\nPASSENGER LIST");
        System.out.println("-----------------------------------------------------");
        System.out.print("PASSENGER NAME      CRUISE              ROOM TYPE");
        System.out.println("\n-----------------------------------------------------");
        for (Passenger eachPassenger: passengerList)
            eachPassenger.printPassenger();
    }

    // display text-based menu
    public static void displayMenu() {

        System.out.println("\n\n");
        System.out.println("\t\t\tLuxury Ocean Cruise Outings");
        System.out.println("\t\t\t\t\tSystem Menu\n");
        System.out.println("[1] Add Ship            [A] Print Ship Names");
        System.out.println("[2] Edit Ship           [B] Print Ship In Service List");
        System.out.println("[3] Add Cruise          [C] Print Ship Full List");
        System.out.println("[4] Edit Cruise         [D] Print Cruise List");
        System.out.println("[5] Add Passenger       [E] Print Cruise Details");
        System.out.println("[6] Edit Passenger      [F] Print Passenger List");
        System.out.println("[x] Exit System");
        System.out.println("\nEnter a menu selection: ");
    }

    // Add a New Ship
    public static void addShip() {

        // complete this method
    	// Ship(String tName, int tBalcony, int tOceanView,
        //int tSuite, int tInterior, boolean tInService)
    	
    	//Create variables
    	String shipName = "";
    	int shipBalconies = 0, shipOceanViews = 0, shipSuites = 0, shipInterior = 0;
    	boolean shipInService = false;

		Scanner scnr = new Scanner(System.in);

    	//Gather user input.
    	try {
    		System.out.println("Enter ship name:");
    		shipName = scnr.nextLine();
    	
    		System.out.println("Enter number of Balcony cabins:");
    		shipBalconies = scnr.nextInt();
    	
    		System.out.println("Enter number of Ocean View cabins:");
    		shipOceanViews = scnr.nextInt();
    	
    		System.out.println("Enter number of Suite cabins:");
    		shipSuites = scnr.nextInt();
    		
    		System.out.println("Enter number of Interior cabins:");
    		shipInterior = scnr.nextInt();
    	
    		System.out.println("Is the ship in service? True/False");
    		shipInService = scnr.hasNextBoolean();
    	}catch(Exception except) {
    		
    		System.out.println("Error: Invalid input. Please try again");
    		String scnrReset = scnr.nextLine();
    	}
    	
    	//Set a variable to false to validate ship doesn't exist.
    	boolean shipExists = false;
    	
    	for(Ship eachShip: shipList) {
    		if(shipName.equals(eachShip.getShipName())) {
    			shipExists = true;
    		}
    	}
    	
    	if(shipExists == false) {
    		//Create a ship object.
    		Ship newShip = new Ship(shipName, shipBalconies, shipOceanViews, shipSuites, shipInterior, shipInService);
    		//Add to the ArrayList 
    		shipList.add(newShip);
    		
    		System.out.println("Ship added");
    	}else {
    		System.out.println("Ship already exists. Cannot add again.");
    	}

    }

    // Edit an existing ship
    public static void editShip() {
        
    	//Start out by selecting which ship to edit. 
        System.out.println("Please select a ship from the list by number: \n");
        printShipList("name");
        Scanner scnr = new Scanner(System.in);
        int shipNumber = 1;
        String scnrReset;
		try {
			shipNumber = scnr.nextInt();
		}catch(Exception except) {
    		System.out.println("Error: Invalid input. Please try again");
    		scnrReset = scnr.nextLine();
    	}
		scnrReset = scnr.nextLine();
		//Print details on the ship.
        System.out.println("--------------------------------------------------------");
        System.out.println("                    Number of Rooms     \tIn");
        System.out.print("SHIP NAME           Bal\tOV \tSte\tInt     Service");
        System.out.println("\n--------------------------------------------------------");
        shipList.get(--shipNumber).printShipData();
    	String shipName = "";
    	int shipBalconies = 0, shipOceanViews = 0, shipSuites = 0, shipInterior = 0;
    	boolean shipInService = false;
    	
    	//Gather user input.
    	try {
    		System.out.println("Enter ship name:");
    		shipName = scnr.nextLine();
    	
    		System.out.println("Enter number of Balcony cabins:");
    		shipBalconies = scnr.nextInt();
    	
    		System.out.println("Enter number of Ocean View cabins:");
    		shipOceanViews = scnr.nextInt();
    	
    		System.out.println("Enter number of Suite cabins:");
    		shipSuites = scnr.nextInt();
    		
    		System.out.println("Enter number of Interior cabins:");
    		shipInterior = scnr.nextInt();
    	
    		System.out.println("Is the ship in service? True/False");
    		shipInService = scnr.hasNextBoolean();
    	}catch(Exception except) {
    		
    		System.out.println("Error: Invalid input. Please try again");
    		scnrReset = scnr.nextLine();
    	}
    	
    	//Reset all the values to the new ones.
    	shipList.get(shipNumber).setInService(shipInService);
    	shipList.get(shipNumber).setRoomInterior(shipInterior);
    	shipList.get(shipNumber).setRoomBalcony(shipBalconies);
    	shipList.get(shipNumber).setRoomOceanView(shipOceanViews);
    	shipList.get(shipNumber).setRoomSuite(shipSuites);
    	shipList.get(shipNumber).setShipName(shipName);

		//Print details on the ship after edit.
    	System.out.println("Ship Details edited!");
        System.out.println("--------------------------------------------------------");
        System.out.println("                    Number of Rooms     \tIn");
        System.out.print("SHIP NAME           Bal\tOV \tSte\tInt     Service");
        System.out.println("\n--------------------------------------------------------");
        shipList.get(shipNumber).printShipData();
        
    	
    }

    // Add a New Cruise
    public static void addCruise() {

        // complete this method
    	//Cruise(String tCruiseName, String tShipName, String tDeparture, String tDestination, String tReturn) 
    	
    	//Create variables. 
    	String cruiseName = "", shipName = "", departure = "", destination = "", returnPort = "";
    	String scnrReset;
		Scanner scnr = new Scanner(System.in);
		
		try {
			System.out.println("Enter Cruise name:");
			cruiseName = scnr.nextLine();
			
			System.out.println("Enter Ship name:");
			shipName = scnr.nextLine();
			
			System.out.println("Enter Departure port:");
			departure = scnr.nextLine();
			
			System.out.println("Enter Destination port:");
			destination = scnr.nextLine();
			
			System.out.println("Enter Return port:");
			returnPort = scnr.nextLine();
	
		}catch(Exception except) {
			
			System.out.println("Error: Invalid input. Please try again");
			scnrReset = scnr.nextLine();
		}
		
		
    	//Set a variable to false to validate ship doesn't exist.
    	boolean cruiseExists = false;
    	
    	for(Cruise eachCruise: cruiseList) {
    		if(cruiseName.equals(eachCruise.getCruiseName())) {
    			cruiseExists = true;
    		}
    	}
    	
    	if(cruiseExists == false) {
    		//Create a cruise object.
    		Cruise newCruise = new Cruise(cruiseName, shipName, departure, destination, returnPort);
    		//Add to the ArrayList 
    		cruiseList.add(newCruise);
    		
    		System.out.println("Cruise added.");
    	}else {
    		System.out.println("Cruise already exists. Cannot add again.");
    	}

        
    }

    // Edit an existing cruise
    public static void editCruise() {

    	//Start out by selecting which cruise to edit. 
        System.out.println("Please select a cruise from the list by number: \n");
        printCruiseList("list");
        Scanner scnr = new Scanner(System.in);
        int cruiseNumber = 1;
        String scnrReset;
		try {
			cruiseNumber = scnr.nextInt();
		}catch(Exception except) {
    		System.out.println("Error: Invalid input. Please try again");
    		scnrReset = scnr.nextLine();
    	}
		scnrReset = scnr.nextLine();
		//Print details on the cruise.
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("                                      |----------------------PORTS-----------------------|");
        System.out.print("CRUISE NAME         SHIP NAME           DEPARTURE           DESTINATION         RETURN");
        System.out.println("\n-----------------------------------------------------------------------------------------");
        cruiseList.get(--cruiseNumber).printCruiseDetails();

    	String cruiseName = "", shipName = "", departure = "", destination = "", returnPort = "";
    	
    	//Gather user input.
    	try {
			System.out.println("Enter Cruise name:");
			cruiseName = scnr.nextLine();
			
			System.out.println("Enter Ship name:");
			shipName = scnr.nextLine();
			
			System.out.println("Enter Departure port:");
			departure = scnr.nextLine();
			
			System.out.println("Enter Destination port:");
			destination = scnr.nextLine();
			
			System.out.println("Enter Return port:");
			returnPort = scnr.nextLine();
	
    	}catch(Exception except) {
    		
    		System.out.println("Error: Invalid input. Please try again");
    		scnrReset = scnr.nextLine();
    	}
    	
    	//Reset all the values to the new ones.
    	cruiseList.get(cruiseNumber).setCruiseName(cruiseName);
    	cruiseList.get(cruiseNumber).setCruiseShipName(shipName);
    	cruiseList.get(cruiseNumber).setDeparturePort(departure);
    	cruiseList.get(cruiseNumber).setDestination(destination);
    	cruiseList.get(cruiseNumber).setReturnPort(returnPort);

		//Print details on the ship after edit.
    	System.out.println("Cruise details edited!");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("                                      |----------------------PORTS-----------------------|");
        System.out.print("CRUISE NAME         SHIP NAME           DEPARTURE           DESTINATION         RETURN");
        System.out.println("\n-----------------------------------------------------------------------------------------");
        cruiseList.get(cruiseNumber).printCruiseDetails();

    }

    // Add a New Passenger
    public static void addPassenger() {

        Scanner newPassengerInput = new Scanner(System.in);
        System.out.println("Enter the new passenger's name: ");
        String newPassengerName = newPassengerInput.nextLine();

        //Run a loop to check, and make sure 
        // ensure new passenger name does not already exist
        for (Passenger eachPassenger: passengerList) {
            if (eachPassenger.getPassengerName().equalsIgnoreCase(newPassengerName)) {
                System.out.println("That passenger is already in the system. Exiting to menu...");
                return; // quits addPassenger() method processing
            }
        }

        // get cruise name for passenger
        System.out.println("Enter cruise name: ");
        String newCruiseName = newPassengerInput.nextLine();

        // ensure cruise exists
        for (Cruise eachCruise: cruiseList) {
            if (eachCruise.getCruiseName().equalsIgnoreCase(newCruiseName)) {
                // cruise does exist
            } else {
                System.out.println("That cruise does not exist in the system. Exiting to menu...");
                return; // quits addPassenger() method processing
            }
        }

        // get room type
        System.out.println("Enter Room Type (BAL, OV, STE, or INT): ");
        String room = newPassengerInput.nextLine();
        // validate room type
        if ((room.equalsIgnoreCase("BAL")) || (room.equalsIgnoreCase("OV")) ||
                (room.equalsIgnoreCase("STE")) || (room.equalsIgnoreCase("INT"))) {
            // validation passed - add passenger
            Passenger newPassenger = new Passenger(newPassengerName, newCruiseName, room.toUpperCase());
            passengerList.add(newPassenger);
        } else {
            System.out.println("Invalid input. Exiting to menu...");
            return; // quits addPassenger() method processing
        }
    }

    // Edit an existing passenger
    public static void editPassenger() {

    	System.out.println("Please select a passenger from the list by number: \n");
        //Since we don't have a nice function to print just a list of passenger names, we're doing that here.
        for (int i = 0; i < passengerList.size(); i++) {
        	int n = i + 1;
            System.out.println("Passenger "+ n + ": " + passengerList.get(i));
        }

        Scanner scnr = new Scanner(System.in);
        int passengerNumber = 1;
        String scnrReset;
		try {
			passengerNumber = scnr.nextInt();
		}catch(Exception except) {
    		System.out.println("Error: Invalid input. Please try again");
    		scnrReset = scnr.nextLine();
    	}
		
		scnrReset = scnr.nextLine();
		
        System.out.println("-----------------------------------------------------");
        System.out.print("PASSENGER NAME      CRUISE              ROOM TYPE");
        System.out.println("\n-----------------------------------------------------");
        passengerList.get(--passengerNumber).printPassenger();
        
        
        System.out.println("Enter the passenger's name: ");
        String newPassengerName = scnr.nextLine();
        
        //Run a loop to check, and make sure 
        // ensure new passenger name does not already exist
        for (Passenger eachPassenger: passengerList) {
            if (eachPassenger.getPassengerName().equalsIgnoreCase(newPassengerName)) {
                System.out.println("That passenger is already in the system. Exiting to menu...");
                return; // quits addPassenger() method processing
            }
        }

        // get cruise name for passenger
        System.out.println("Enter cruise name: ");
        String newCruiseName = scnr.nextLine();

        // ensure cruise exists
        for (Cruise eachCruise: cruiseList) {
            if (eachCruise.getCruiseName().equalsIgnoreCase(newCruiseName)) {
                // cruise does exist
            } else {
                System.out.println("That cruise does not exist in the system. Exiting to menu...");
                return; // quits addPassenger() method processing
            }
        }

        // get room type
        System.out.println("Enter Room Type (BAL, OV, STE, or INT): ");
        String room = scnr.nextLine();
        // validate room type
        if ((room.equalsIgnoreCase("BAL")) || (room.equalsIgnoreCase("OV")) ||
                (room.equalsIgnoreCase("STE")) || (room.equalsIgnoreCase("INT"))) {
            // validation passed - edit passenger
        	passengerList.get(passengerNumber).setPassengerName(newPassengerName);
        	passengerList.get(passengerNumber).setPassengerCruise(newCruiseName);
        	passengerList.get(passengerNumber).setPassengerRoomType(room);
        } else {
            System.out.println("Invalid input. Exiting to menu...");
            return; // quits method processing
        }
        
        System.out.println("Passenger edited sucessfully.");
        System.out.println("-----------------------------------------------------");
        System.out.print("PASSENGER NAME      CRUISE              ROOM TYPE");
        System.out.println("\n-----------------------------------------------------");
        passengerList.get(passengerNumber).printPassenger();
        
    }

    // Method to check if input is a number
    public static boolean isANumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) == false)
                return false;
        }
        return true;
    }

}
