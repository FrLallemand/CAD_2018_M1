package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modeles.Terrain;

public class VueTerrain extends JPanel implements Observer{

 	protected static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
 	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 25;
	protected static final int HEIGHT = 25;

	private int decalage = 1;
	private Terrain terrain;
	
	public VueTerrain(Terrain t) {
		terrain = t;
		this.setLayout(new GridLayout(t.getHeight()+decalage, t.getWidth()+decalage));
		this.setPreferredSize(new Dimension((t.getHeight()+decalage)*HEIGHT,(t.getHeight()+decalage)*WIDTH));
		this.initialize();
		this.setVisible(true);
	}
	
	
	public void initialize() {
		this.add(new JLabel("",SwingConstants.CENTER));
		for(int w=1;w<terrain.getWidth()+1;w++){
			this.add(new JLabel(String.valueOf(w),SwingConstants.CENTER));		
		}
		System.out.println(terrain.getHeight()+decalage);
		for(int w=0;w<terrain.getWidth();w++){
			this.add(new JLabel(String.valueOf(alphabet[w]),SwingConstants.CENTER));
			for(int h=0;h<terrain.getHeight();h++){
				JButton tmp = new JButton();
				tmp.setBackground(Color.BLUE);
				this.add(tmp);
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
