package socialdistancing;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

public class Building {

	List<Wall> walls;
	BuildingLabel label;

	public Building(List<Wall> walls, BuildingLabel label) {
		super();// refer to parent object
		this.walls = walls;//refer to buliding(wall is atribute)
		this.label = label;
	}

	public Building(BuildingLabel label, Wall ... walls) {
		this(Arrays.asList(walls), label);
	}

	public void paint(Graphics g, JPanel view) {
		for (Wall wall : walls) {
			wall.paint(g, view);//draw on jframe
		}
		label.paint(g);
	}
}
