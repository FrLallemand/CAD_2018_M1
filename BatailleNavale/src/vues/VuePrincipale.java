package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import modeles.BatailleNavale;
import modeles.Modele;

public class VuePrincipale extends JPanel implements Observer{

	private JFrame affichage;
	private JPanel playerView;
	private JPanel playerViewTerrain;
	private JPanel ennemyView;
	private JPanel ennemyViewTerrain;
	private JPanel optionInfoTop;
	private ButtonGroup orientation;
	private JButton nouveau;
	private JButton sauvegarder;
	private JButton charger;
	
	private Modele modele;
	private VueTerrain terrainJoueur, terrainTir;

	public VuePrincipale(Modele m){
		this.modele = m;

		//init JFrame
		JFrame affichage = new JFrame("Bataille Navale");
		affichage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		affichage.setContentPane(this);
		affichage.setVisible(true);
		affichage.setSize(800, 400);

		this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(25, 25, 25, 25);
        
		terrainJoueur = new VueTerrain(modele.getTerrain());		
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(terrainJoueur, constraints);

		JPanel separator= new JPanel();		
		separator.setBackground(Color.black);
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(separator, constraints);

		terrainTir = new VueTerrain(modele.getTerrain());		
        constraints.gridx = 2;
        constraints.gridy = 1;
        this.add(terrainTir, constraints);

        this.setVisible(true);
        		
		//affichage menu/option/information supplementaires
		optionInfoTop=new JPanel();
		//selection orientation lors du placement des bateaux
		optionInfoTop.add(new JLabel("Orientation :"));
		orientation = new ButtonGroup();
		JRadioButton H= new JRadioButton("Horizontal",true);
		JRadioButton V= new JRadioButton("Vertical");
		orientation.add(V);
		orientation.add(H);
		optionInfoTop.add(H);
		optionInfoTop.add(V);
		//partie menu
		nouveau=new JButton("Nouveau");
		sauvegarder=new JButton("Sauvegarder");
		charger=new JButton("Charger");
		optionInfoTop.add(nouveau);
		optionInfoTop.add(sauvegarder);
		optionInfoTop.add(charger);
		
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(optionInfoTop,constraints);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
