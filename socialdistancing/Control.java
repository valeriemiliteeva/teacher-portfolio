package socialdistancing;

import java.util.ArrayList;

public class Control {
	String title = "Social Distance Simulation";
	// Model and View
	BlockFrame view; // JPanel graphics window

	// counters for "this" simulation instance
	public int numInfected = 0;
	public int numDied = 0;

	private Settings settings;

	/*
	 * Default constructor uses Static/Default simulation values
	 */
	public Control() {
		// This sets defaults in case run with default constructor
		// simulation control starting values
		settings = new Settings(null);
		settings.numPeople = Settings.sNumPeople;
		settings.toRoam = Settings.sToRoam;
		settings.toBeInfected = Settings.sToBeInfected;
		settings.toDie = Settings.sToDie;
		settings.sickTimeLow = Settings.sSickTimeLow;
		settings.sickTimeMax = Settings.sSickTimeMax;
		// frame extents
		settings.frameX = Settings.sFrameX;
		settings.frameY = Settings.sFrameY;
		// position extents, keep objects away from the edges
		settings.xExt = Settings.sXExt;
		settings.yExt = Settings.sYExt;
		// oval size, represents person in frame
		settings.OvalW = Settings.sOvalW; // Height
		settings.OvalH = Settings.sOvalH; // Width
		// refresh timer, also used to calculate time/age of infection
		settings.timerValue = Settings.sTimerValue;
	}

	/*
	 * This constructor uses user defined simulation Settings
	 */
	public Control(Settings sets) {
		// health settings
		this.settings = sets;
	}

	/*
	 * Tester method to run simulation
	 */
	public static void main(String[] args) {
		Control c = new Control();
		c.runSimulation();
	}

	/*
	 * This method coordinates MVC for Simulation - The Simulation is managing
	 * People in a Graphics frame to simulate a virus outbreak - Prerequisite:
	 * Control values from constructor are ready
	 */
	public void runSimulation() {

		// Setup the People
		ArrayList<Person> people = new ArrayList<>();
		for (int i = 0; i < settings.numPeople; i++) {
			// instantiate Person object and add it to the ArrayList
			people.add(new Person(settings));
		}

		// Setup the Buildings
		ArrayList<Building> buildings = new ArrayList<>();
		buildings.add(BLDG1);
		buildings.add(BLDG2);
		buildings.add(BLDG3);
		buildings.add(BLDG4);

		// Setup to the Simulation Panel/Frame
		Block block = new Block(buildings, people);

		view = new BlockFrame(settings, title, block);
		// Start the Simulation
		view.activate();
	}

	static BuildingLabel BLDG_LABEL1 = new BuildingLabel("Val's House", 610, 50);
	static BuildingLabel BLDG_LABEL2 = new BuildingLabel("Alex's House", 5, 50);
	static BuildingLabel BLDG_LABEL3 = new BuildingLabel("Abhinav's House", 5, 440);
	static BuildingLabel BLDG_LABEL4 = new BuildingLabel("Karl Strauss", 590, 440);

//Declares Wall sprites and positions of walls

	static Building BLDG1 = new Building(BLDG_LABEL1, new Wall(550, 0, "SocialDistancingImages/wall2.png", true),
			new Wall(620, 160, "SocialDistancingImages/wall1.png", false));

	static Building BLDG2 = new Building(BLDG_LABEL2, new Wall(200, 0, "SocialDistancingImages/wall2.png", true),
			new Wall(-25, 160, "SocialDistancingImages/wall1.png", false));

	static Building BLDG3 = new Building(BLDG_LABEL3,
			new Wall(550, 400, "SocialDistancingImages/wall2.png", true),
			new Wall(620, 400, "SocialDistancingImages/wall1.png", false));

	static Building BLDG4 = new Building(BLDG_LABEL4,
			new Wall(200, 400, "SocialDistancingImages/wall2.png", true),
			new Wall(-25, 400, "SocialDistancingImages/wall1.png", false));

}