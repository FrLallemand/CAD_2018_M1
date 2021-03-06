package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Controleurs.PlacementControleur;
import Controleurs.TirControleur;
import modeles.Terrain;
import modeles.Modele;
import modeles.Strategie.NomsStrategies;
import modeles.Terrain.StatusCase;
import modeles.epoques.Epoque.NomsEpoques;

public class VueTerrain extends JPanel {

 	protected static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
 	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 25;
	protected static final int HEIGHT = 25;
	protected static final Color SEA = Color.BLUE;

	private int decalage = 1;
	private Modele modele;
	private Terrain terrain;
	private JButton[][] tiles;
	private boolean estTerrainTir;
	
	public VueTerrain(Modele m, boolean terrainTir) {
		modele = m;
		estTerrainTir = terrainTir;
		if(terrainTir) {			
			terrain = m.getTerrainJ2();
		} else {
			terrain = m.getTerrainJ1();
		}
		this.setLayout(new GridLayout(terrain.getHeight()+decalage, terrain.getWidth()+decalage));
		this.setPreferredSize(new Dimension((terrain.getHeight()+decalage)*HEIGHT,(terrain.getHeight()+decalage)*WIDTH));
		this.tiles = new JButton[terrain.getHeight()][terrain.getWidth()];
		this.initialize(null);
		this.setVisible(true);
	}
	
	public VueTerrain(Modele m, JComboBox orientation) {
		modele = m;
		estTerrainTir = false;
		if(estTerrainTir) {			
			terrain = m.getTerrainJ2();
		} else {
			terrain = m.getTerrainJ1();
		}
		this.setLayout(new GridLayout(terrain.getHeight()+decalage, terrain.getWidth()+decalage));
		this.setPreferredSize(new Dimension((terrain.getHeight()+decalage)*HEIGHT,(terrain.getHeight()+decalage)*WIDTH));
		this.tiles = new JButton[terrain.getHeight()][terrain.getWidth()];
		this.initialize(orientation);
		this.setVisible(true);
	}
	
	
	public void initialize(JComboBox orientation) {
		this.add(new JLabel("",SwingConstants.CENTER));
		for(int w=1;w<terrain.getWidth()+1;w++){
			this.add(new JLabel(String.valueOf(w),SwingConstants.CENTER));		
		}
		for(int h=0;h<terrain.getHeight();h++){
			this.add(new JLabel(String.valueOf(alphabet[h]),SwingConstants.CENTER));
			for(int w=0;w<terrain.getWidth();w++){
				JButton tmp = new JButton();
				tmp.setEnabled(false);
				tmp.setBackground(Color.BLUE);
				this.add(tmp);
				tiles[h][w] = tmp;
				if(this.estTerrainTir){
					tiles[h][w].addActionListener(new TirControleur(modele, w, h));
				}else{
					tiles[h][w].addActionListener(new PlacementControleur(modele, w, h, orientation));
					
				}
			}
		}
	}
	
	public void activeTerrain() {
		for(int h=0;h<terrain.getHeight();h++){
			for(int w=0;w<terrain.getWidth();w++){
				tiles[h][w].setEnabled(true);
			}
		}
	}


	public void desactiveTerrain() {
		for(int h=0;h<terrain.getHeight();h++){
			for(int w=0;w<terrain.getWidth();w++){
				tiles[h][w].setEnabled(false);
			}
		}
	}

	public void update() {
		for(int w=0;w<terrain.getWidth();w++){
			for(int h=0;h<terrain.getHeight();h++){
				StatusCase[][] cases = terrain.getCases(); 
				JButton tmp = tiles[w][h];
				switch (cases[w][h]) {
				case BATEAU:
					if(this.estTerrainTir) {	
						tmp.setBackground(Color.BLUE);
					} else {
						tmp.setBackground(Color.GREEN);						
					}
					break;
				case EAU:
					tmp.setBackground(Color.BLUE);
					break;
				case BATEAUTOUCHE:
					tmp.setBackground(Color.RED);
					break;
				case EAUTOUCHE:
					tmp.setBackground(Color.CYAN);
					break;
				case COULE:
					tmp.setBackground(Color.BLACK);
					break;
				default:
					break;
				}

				
			}
		}		
	}
}
