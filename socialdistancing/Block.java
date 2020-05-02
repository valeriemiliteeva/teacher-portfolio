package socialdistancing;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Block {

    ArrayList<Building> buildings;
    ArrayList<Person> people;

    public Block(ArrayList<Building> buildings, ArrayList<Person> people) {
        super();
        this.buildings = buildings;
        this.people = people;
    }

    public void paint(Graphics g, JPanel view) {
        for (Building building : buildings) {
            building.paint(g, view);
        }
        for (Person person : people) {
            person.paint(g);
        }
    }

    public void handleLife() {
        for (Person pDot1 : people) {
            for (Person pDot2 : people) {
                // for each unique pair invoke the collision detection code
                pDot1.collisionDetector(pDot2);
            }
            personToWallCollision(pDot1);
            pDot1.healthManager(); // manage health values of the Person
            pDot1.velocityManager(); // manage social distancing and/or roaming values of the Person

        }

    }

    public void personToWallCollision(Person p) {

        ArrayList<Wall> walls = getWalls();
        Rectangle personRect = new Rectangle(p.x, p.y, p.width, p.height);
        for (int i = 0; i < walls.size(); i++) {
            if (walls.get(i).getBounds().intersects(personRect))
                if (walls.get(i).vertical) {
                    p.vx *= -1;
                }
                else
                {
                    p.vy *= -1;
                }
        }
    }

	private ArrayList<Wall> getWalls() {
		ArrayList<Wall> walls = new ArrayList<>();
		for (Building building : buildings) {
			walls.addAll(building.walls);
		}
		return walls;// store walls in arraylist
	}

}
