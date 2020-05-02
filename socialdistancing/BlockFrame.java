package socialdistancing;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


/* 
	Building extends JPanel so that we can override the paint method. The paint method is necessary to use the simple
	drawing tools of the library! 
	Simulator implements an ActionListener which adds the method actionPerformed. This method is invoked by the 
	animation timer every timerValue(16ms).
*/
public class BlockFrame extends JPanel implements ActionListener{
	// serial suppresses warning
	private static final long serialVersionUID = 1L;
	
	//simulation control objects/values
	JFrame frame;
	Timer timer; //Event control	
	int time = 0; //Track time as the simulation runs
	Settings settings;
	Block block;
	
	/* constructor will setup our main Graphic User Interface - a simple Frame! */
	public BlockFrame(Settings sets, String title, Block block) {
		// used for Control callback
		this.settings = sets;
		this.block = block;
		
		//Setup the GUI
		frame = new JFrame(title);
		frame.setSize(sets.frameX, sets.frameY); //set the size
		
		//add this so that hitting the x button will actually end the program
		//the program will continue to run behind the scenes and you might end up with 10+ of them
		//without realizing it
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//make it visible
		frame.setVisible(true);
		frame.add(this); //add this class (JPanel) to the JFrame
	}
	
	//activation of Simulator separated from Constructor 
	public void activate() {
		//Timer for animation
		//Argument 1: timerValue is a period in milliseconds to fire event
		//Argument 2:t any class that "implements ActionListener"
		timer = new Timer(settings.timerValue, this); //timer constructor
		timer.restart(); //restart or start
		
		// frame becomes visible
		frame.setVisible(true);		
	}
	
	/* This invoked by Timer per period in milliseconds in timerValue  */
	@Override
	public void actionPerformed(ActionEvent e) {
		block.handleLife();
		//Triggers paint call through polymorphism
		repaint();	
	}

	/* paint method for drawing the simulation and animation */
	@Override
	public void paint(Graphics g) {
		
		//tracking total time manually with the time variable
		time += settings.timerValue;
		
		//events
		super.paintComponent(g); // a necessary call to the parent paint method, required for proper screen refreshing
		block.paint(g, this);
		paintPersonsColumn(g);
	} 
	
	private void paintPersonsColumn(Graphics g) {
		int index = 0;
		for (Person person : block.people) {
			g.setColor(person.getColor());
			g.fillOval((settings.frameX - (int) (settings.frameX * .02)), (int) (settings.frameY - 
					((settings.numPeople - index) * settings.OvalH) / 1.67), settings.OvalW,
					settings.OvalH);
			index++;
		}
	}
		
	
}