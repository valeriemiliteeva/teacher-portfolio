package socialdistancing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Wall{

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;
    protected boolean vertical;

    public Wall(int x, int y, String imageS, boolean vertical) {
        // object wall
        this.x = x;
        this.y = y;
        visible = true;
        this.vertical = vertical;
        loadImage(imageS);
        getImageDimensions();
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    protected void getImageDimensions() {

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }    

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;// x cord
    }

    public int getY() {
        return y;// y cord
    }

    public boolean isVisible() {
        return visible;//jrame
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public void paint(Graphics g, JPanel view)  {
        g.drawImage(getImage(), getX(), getY(), view);
        //draws with the provided info

    }
}