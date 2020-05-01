package socialdistancing;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

public class NewBuilding {

	List<Wall> walls;
	BuildingLabel label;
	
	public NewBuilding(List<Wall> walls, BuildingLabel label) {
		super();
		this.walls = walls;
		this.label = label;
	}
	
	public NewBuilding(BuildingLabel label, Wall ... walls) {
		this(Arrays.asList(walls), label);
	}

	public void paint(Graphics g, JPanel view) {
		for (Wall wall : walls) {
			wall.paint(g, view);
		}
		label.paint(g);
	}
}
