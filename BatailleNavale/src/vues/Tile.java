package vues;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Tile extends JComponent{
	
	public Tile(){
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().setColor(Color.BLUE);
        g.drawImage(image, 0, 0, 10, 10, 0, 0,
        		10, 10, null);
	}

}
