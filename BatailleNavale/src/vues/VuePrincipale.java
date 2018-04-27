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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import Controleurs.ChargementControlleur;
import Controleurs.NouvellePartieControleur;
import Controleurs.PlacementControleur;
import Controleurs.SauvegardeControlleur;
import modeles.BatailleNavale;
import modeles.Modele;
import modeles.Modele.GameState;
import modeles.Modele.Joueur;
import modeles.Position;

public class VuePrincipale extends JPanel implements Observer{

	private JFrame affichage;
	private JPanel playerView;
	private JPanel playerViewTerrain;
	private JPanel ennemyView;
	private JPanel ennemyViewTerrain;
	private JPanel optionInfoTop;
	private JButton nouveau;
	private JButton sauvegarder;
	private JButton charger;	
	private JButton placementAleatoire;
	private JLabel fin;
	private JComboBox orientation;
	private JLabel labelBateau;
	private Modele modele;
	private VueTerrain terrainJoueur, terrainTir;

	public VuePrincipale(Modele m){
		this.modele = m;
		this.modele.addObserver(this);
		//init JFrame
		affichage = new JFrame("Bataille Navale");
		affichage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		affichage.setContentPane(this);
		affichage.setVisible(true);
		affichage.setSize(900, 600);

		this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(25, 25, 25, 25);
		orientation=new JComboBox(Position.Direction.values());

        // Terrain affichant les bateaux du joueur
		terrainJoueur = new VueTerrain(modele, orientation);		
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(terrainJoueur, constraints);

		JPanel separator= new JPanel();		
		separator.setBackground(Color.black);
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(separator, constraints);

        // Terrain affichant les tirs fait par le joueur
		terrainTir = new VueTerrain(modele, true);		
        constraints.gridx = 2;
        constraints.gridy = 1;
        this.add(terrainTir, constraints);

		placementAleatoire=new JButton("Placement aléatoire");
		placementAleatoire.addActionListener(new PlacementControleur(this.modele));
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(placementAleatoire, constraints);
        

        //selection orientation lors du placement des bateaux
		orientation.setSelectedIndex(0);
        constraints.insets = new Insets(0, 0, 2, 0);
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(orientation, constraints);
        labelBateau = new JLabel();
        constraints.gridy = 4;
        this.add(labelBateau, constraints);


        this.setVisible(true);
        		
		//affichage menu/option/information supplementaires
		optionInfoTop=new JPanel();
		
		//partie menu
		nouveau=new JButton("Nouveau");
		sauvegarder=new JButton("Sauvegarder");
		charger=new JButton("Charger");
		optionInfoTop.add(nouveau);
		optionInfoTop.add(sauvegarder);
		optionInfoTop.add(charger);
		
		nouveau.addActionListener(new NouvellePartieControleur(this));
		sauvegarder.addActionListener(new SauvegardeControlleur(this.sauvegarder,this.modele));
		charger.addActionListener(new ChargementControlleur(this.charger,this.modele));
		
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(optionInfoTop,constraints);
		
		
		fin= new JLabel();
        fin.setVisible(false);
		constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        
        //constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(fin,constraints);	
		
	}
	
	public void disablePlacement() {
		this.placementAleatoire.setEnabled(false);
		this.placementAleatoire.setVisible(false);
		//this.textOrientation.setVisible(false);
		this.orientation.setVisible(false);
		this.labelBateau.setVisible(false);
	}

	public void enablePlacement() {
		this.placementAleatoire.setEnabled(true);
		this.placementAleatoire.setVisible(true);
		//this.textOrientation.setVisible(true);
		this.labelBateau.setVisible(true);
		this.orientation.setVisible(true);
	}

	public void demandePlacementBateaux() {
		this.enablePlacement();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(this.modele.getState() == GameState.DEMANDEBATEAUX && this.modele.getJoueur() == Joueur.J1) {
			this.terrainJoueur.activeTerrain();
			this.terrainJoueur.update();
			this.labelBateau.setText("Bateau à placer: " + this.modele.demandeChoixBateau().getNom());
			// Demande de bateaux sur l'interface, puis passer au controleur
		}else{
			this.terrainJoueur.desactiveTerrain();
		}
		if(this.modele.getState() == GameState.ENCOURS && this.modele.getJoueur() == Joueur.J1) {
			// Tour du joueur 1 (humain), affichage des bateaux, maj des terrains
			this.disablePlacement();
			this.terrainTir.activeTerrain();
			this.terrainJoueur.update();
			this.terrainTir.update();
		}
		if(this.modele.getState() == GameState.ENCOURS && this.modele.getJoueur() == Joueur.J2) {
			this.terrainTir.desactiveTerrain();
			this.terrainJoueur.update();
			this.terrainTir.update();			
		}		
		if(this.modele.getState() == GameState.FIN) {
			if(this.modele.getJoueurGagnant() == Joueur.J1) {
		        fin.setVisible(true);
		        fin.setText("Gagnant : Joueur 1");
			} else {
		        fin.setVisible(true);
		        fin.setText("Gagnant : Joueur 2");
			}
			this.terrainTir.desactiveTerrain();
			this.terrainJoueur.update();
			this.terrainTir.update();		
		}
	}

	public void destroy() {
		this.affichage.dispose();
	}

}
