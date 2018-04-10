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

	private JToggleButton epoqueModerne;
	private JToggleButton epoqueXVII;
	private Map<String,NomsEpoques> map_epoques;

	private ButtonGroup strategies;
	private JToggleButton strategieAleatoire;
	private JToggleButton strategieCroix;
	
	private JButton commencer;

	private Map<String,NomsStrategies> map_strategies;

	private JFrame frame = new JFrame("Options");
	private BatailleNavale modele;

    public ChoixOptions(BatailleNavale m) {
		this.modele = m;
		this.map_epoques = new HashMap<String, Epoque.NomsEpoques>();
		this.map_strategies= new HashMap<String, Strategie.NomsStrategies>();
			
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
		map_epoques.put(NomsEpoques.MODERNE.name(), NomsEpoques.MODERNE);
		map_epoques.put(NomsEpoques.XVII.name(), NomsEpoques.XVII);
		JLabel epoqueLabel = new JLabel("Époque :", SwingConstants.CENTER);
		
		epoqueModerne = new JToggleButton("Moderne");
		epoqueModerne.setSelected(true);
		epoqueModerne.setActionCommand(NomsEpoques.MODERNE.name());
		epoqueXVII = new JToggleButton("XVIIème siècle");		
		epoqueXVII.setActionCommand(NomsEpoques.XVII.name());
		epoques = new ButtonGroup();
		epoques.add(epoqueModerne);
		epoques.add(epoqueXVII);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		contentPane.add(epoqueLabel, constraints);
		constraints.gridx = 1;
		contentPane.add(epoqueModerne, constraints);
		constraints.gridx = 2;
		contentPane.add(epoqueXVII, constraints);
		
		// Strategies
		map_strategies.put(NomsStrategies.ALEATOIRE.name(), NomsStrategies.ALEATOIRE);
		map_strategies.put(NomsStrategies.CROIX.name(), NomsStrategies.CROIX);
		JLabel strategieLabel = new JLabel("Strategie :", SwingConstants.CENTER);
		
		strategieAleatoire = new JToggleButton("Aléatoire");
		strategieAleatoire.setSelected(true);
		strategieAleatoire.setActionCommand(NomsStrategies.ALEATOIRE.name());
		strategieCroix = new JToggleButton("Croix");
		strategieCroix.setActionCommand(NomsStrategies.CROIX.name());
		
		strategies = new ButtonGroup();
		strategies.add(strategieAleatoire);
		strategies.add(strategieCroix);
        JPanel panelStrategie= new JPanel();
        panelStrategie.setLayout(new BoxLayout(panelStrategie, BoxLayout.X_AXIS));

		constraints.gridx = 0;
		constraints.gridy = 1;
        contentPane.add(strategieLabel, constraints);
		constraints.gridx = 1;
		contentPane.add(strategieAleatoire, constraints);
		constraints.gridx = 2;
		contentPane.add(strategieCroix, constraints);

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



	public NomsEpoques getEpoque(String e) {
		return map_epoques.get(e);
	}
	
	public NomsStrategies getStrategie(String s) {
		return map_strategies.get(s);
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
