package socialdistancing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Control {
	String title = "Social Distance Simulation";
	// Model and View
	ArrayList<Person> model; // the community of Person objects
	Building view; // JPanel graphics window

	// counters for "this" simulation instance
	public int numInfected = 0;
	public int numDied = 0;

	// simulation control values
	public int numPeople;
	public double toRoam;
	public double toBeInfected;
	public double toDie;
	public int sickTimeLow;
	public int sickTimeMax;
	// frame extents
	public int frameX;
	public int frameY;
	// position extents, keep objects away from the edges
	public int xExt;
	public int yExt;
	// oval size, represents person in frame
	public int OvalW; // Height
	public int OvalH; // Width
	// refresh timer, also used to calculate time/age of infection
	public int timerValue;
	private Settings settings;

	/*
	 * Default constructor uses Static/Default simulation values
	 */
	public Control() {
		// This sets defaults in case run with default constructor
		// simulation control starting values
		numPeople = Settings.sNumPeople;
		toRoam = Settings.sToRoam;
		toBeInfected = Settings.sToBeInfected;
		toDie = Settings.sToDie;
		sickTimeLow = Settings.sSickTimeLow;
		sickTimeMax = Settings.sSickTimeMax;
		// frame extents
		frameX = Settings.sFrameX;
		frameY = Settings.sFrameY;
		// position extents, keep objects away from the edges
		xExt = Settings.sXExt;
		yExt = Settings.sYExt;
		// oval size, represents person in frame
		OvalW = Settings.sOvalW; // Height
		OvalH = Settings.sOvalH; // Width
		// refresh timer, also used to calculate time/age of infection
		timerValue = Settings.sTimerValue;
	}

	/*
	 * This constructor uses user defined simulation Settings
	 */
	public Control(Settings sets) {
		// health settings
		this.settings = sets;
		numPeople = sets.numPeople;
		toRoam = sets.toRoam;
		toBeInfected = sets.toBeInfected;
		toDie = sets.toDie;
		sickTimeLow = sets.sickTimeLow;
		sickTimeMax = sets.sickTimeMax;
		// simulator settings
		frameX = sets.frameX;
		frameY = sets.frameY;
		yExt = sets.yExt;
		xExt = sets.xExt;
		OvalW = sets.OvalW;
		OvalH = sets.OvalH;
		timerValue = sets.timerValue;
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
		// Setup to the Simulation Panel/Frame
		NewBlock block = new NewBlock(this, title);

		// Setup the People
		model = new ArrayList<Person>();
		for (int i = 0; i < numPeople; i++) {
			// instantiate Person object and add it to the ArrayList
			model.add(new Person(this));
		}

		// Start the Simulation
		view.activate();
	}

	/*
	 * Call Back method for View paints/repaints model of graphic objects repressing
	 * person objects in the frame
	 */
	public void paintPersons(Graphics g) {

		// find the Person in the Model!
		int index = 0;
		for (Person pDot1 : model) {
			for (Person pDot2 : model) {
				// for each unique pair invoke the collision detection code
				pDot1.collisionDetector(pDot2);
			}
			personToWallCollision(pDot1);
			pDot1.healthManager(); // manage health values of the Person
			pDot1.velocityManager(); // manage social distancing and/or roaming values of the Person

			// set the color of the for the person oval based on the health status of person
			// object
			switch (pDot1.state) {
			case candidate:
				g.setColor(Color.LIGHT_GRAY);
				break;
			case infected:
				g.setColor(Color.red);
				break;
			case recovered:
				g.setColor(Color.green);
				break;
			case died:
				g.setColor(Color.black);

			}

			// draw the person oval in the simulation frame
			g.fillOval(pDot1.x, pDot1.y, OvalW, OvalH);

			// draw the person oval in meter/bar indicator
			g.fillOval((frameX - (int) (frameX * .02)), (int) (frameY - ((numPeople - index) * OvalH) / 1.67), OvalW,
					OvalH);
			index++;

		}
	}

	static BuildingLabel BLDG_LABEL1 = new BuildingLabel("Sprouts", 610, 50);
	static BuildingLabel BLDG_LABEL2 = new BuildingLabel("Scripps Medical", 5, 50);
	static BuildingLabel BLDG_LABEL3 = new BuildingLabel("Board and Brew", 5, 440);
	static BuildingLabel BLDG_LABEL4 = new BuildingLabel("Mr. M's House", 590, 440);

//Declares Wall sprites and positions of walls

	static NewBuilding BLDG1 = new NewBuilding(BLDG_LABEL1, 
			new Wall(550, 0, "SocialDistancingImages/wall2.png", true),
			new Wall(620, 160, "SocialDistancingImages/wall1.png", false));

	static NewBuilding BLDG2 = new NewBuilding(BLDG_LABEL2, 
			new Wall(200, 0, "SocialDistancingImages/wall2.png", true)
			new Wall(-25, 160, "SocialDistancingImages/wall1.png", false));

	static NewBuilding BLDG3 = new NewBuilding(BLDG_LABEL3, 
			new Wall(550, 400, "SocialDistancingImages/wall2.png", true),
			new Wall(620, 400, "SocialDistancingImages/wall1.png", false));

	static NewBuilding BLDG4 = new NewBuilding(BLDG_LABEL4,
			new Wall(200, 400, "SocialDistancingImages/wall2.png", true),
			new Wall(-25, 400, "SocialDistancingImages/wall1.png", false));
		

	public void personToWallCollision(Person p) {

		Rectangle personRect = new Rectangle(p.x, p.y, p.width, p.height);
		for (int i = 0; i < walls.length; i++) {
			if (r[i].intersects(personRect))
				if (walls[i].vertical) {
					p.vx *= -1;
				} else
					p.vy *= -1;
		}
	}

}