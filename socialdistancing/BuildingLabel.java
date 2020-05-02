package socialdistancing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BuildingLabel {

	String name;
	int x, y;

	public BuildingLabel(String name, int x, int y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g) {
		//sets text color
		g.setColor(Color.BLUE);
		g.setFont(new Font("Roboto", Font.BOLD, 20));

		g.drawString(name, x, y);
	}
	
}
