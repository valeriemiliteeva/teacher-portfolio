package socialdistancing;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class NewBlock {

	ArrayList<NewBuilding> buildings = new ArrayList<>();
	ArrayList<Person> people  = new ArrayList<>();

	public NewBlock(ArrayList<NewBuilding> buildings, ArrayList<Person> people) {
		super();
		this.buildings = buildings;
	}
	
	public void paint(Graphics g, JPanel view) {
		for (NewBuilding building : buildings) {
			building.paint(g, view);
		}
		for (Person person : people) {
			person.paint(g);
		}
	}
	
}
