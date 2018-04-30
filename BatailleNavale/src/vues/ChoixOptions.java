package vues;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import Controleurs.OptionsControleur;
import modeles.BatailleNavale;
import modeles.GameFactory;
import modeles.Strategie;
import modeles.Strategie.NomsStrategies;
import modeles.epoques.Epoque;
import modeles.epoques.EpoqueModerne;
import modeles.epoques.EpoqueXVII;
import modeles.epoques.Epoque.NomsEpoques;
import modeles.StrategieAleatoire;
import modeles.StrategieCroix;

public class ChoixOptions implements Observer{

	private ButtonGroup epoques;
	public ButtonGroup getEpoques() {
		return epoques;
	}



	public void setEpoques(ButtonGroup epoques) {
		this.epoques = epoques;
	}

	private ButtonGroup strategies;

	private JButton commencer;

	private JFrame frame = new JFrame("Options");
	private BatailleNavale modele;

	public ChoixOptions(BatailleNavale m) {
		this.modele = m;

		//this.modele.addObserver(this);
		buildFrame();
	}



	public void buildFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(500, 500));
		JPanel contentPane = (JPanel) frame.getContentPane();
		//contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(25, 25, 25, 25);

		// Époques
		JLabel epoqueLabel = new JLabel("Époque :", SwingConstants.CENTER);
		epoques = new ButtonGroup();
		Epoque.NomsEpoques[] listepoques=Epoque.NomsEpoques.values();
		JToggleButton tmpbutton;
		constraints.gridx = 0;
		constraints.gridy = 0;
		contentPane.add(epoqueLabel, constraints);
		for(int x=0;x<listepoques.length;x++){
			tmpbutton = new JToggleButton(listepoques[x].name());
			if(x==0)
				tmpbutton.setSelected(true);
			tmpbutton.setActionCommand(listepoques[x].name());
			epoques.add(tmpbutton);
			constraints.gridx = x+1;
			contentPane.add(tmpbutton, constraints);
		}

		// Strategies
		JLabel strategieLabel = new JLabel("Strategie :", SwingConstants.CENTER);
		strategies = new ButtonGroup();
		Strategie.NomsStrategies[] liststrategie=Strategie.NomsStrategies.values();

		constraints.gridx = 0;
		constraints.gridy = 1;
		contentPane.add(strategieLabel, constraints);

		JPanel panelStrategie= new JPanel();
		panelStrategie.setLayout(new BoxLayout(panelStrategie, BoxLayout.X_AXIS));

		for(int x=0;x<liststrategie.length;x++){
			tmpbutton = new JToggleButton(liststrategie[x].name());
			if(x==0)
				tmpbutton.setSelected(true);
			tmpbutton.setActionCommand(liststrategie[x].name());
			strategies.add(tmpbutton);
			constraints.gridx = x+1;
			contentPane.add(tmpbutton, constraints);

		}

		commencer = new JButton("Commencer");
		constraints.gridx = 1;
		constraints.gridy = 2;
		contentPane.add(commencer, constraints);
		commencer.addActionListener(new OptionsControleur(this, this.modele));
		frame.pack();
		//frame.setVisible(true);

	}

	public ButtonGroup getStrategies() {
		return strategies;
	}



	public void setStrategies(ButtonGroup strategies) {
		this.strategies = strategies;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		this.frame.dispose();
	}

	public void visible() {
		this.frame.setVisible(true);		
	}

}
