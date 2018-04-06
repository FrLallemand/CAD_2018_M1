package vues;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

import modeles.BatailleNavale;
import modeles.Epoque;
import modeles.EpoqueModerne;
import modeles.EpoqueXVII;
import modeles.Epoque.NomsEpoques;

public class ChoixOptions implements Observer{

	private ButtonGroup epoques;
	private JToggleButton epoqueModerne;
	private JToggleButton epoqueXVII;
    private JFrame frame = new JFrame("Options");

	private Map<String, Epoque> map_epoques;
	BatailleNavale modele;

    public ChoixOptions(BatailleNavale m) {
		this.modele = m;
		this.map_epoques = new HashMap<String, Epoque>();
			
		//this.modele.addObserver(this);
		buildFrame();
	}
    
 

    public void buildFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(new GridLayout());
        		
		map_epoques.put(NomsEpoques.MODERNE.name(), new EpoqueModerne());
		map_epoques.put(NomsEpoques.XVII.name(), new EpoqueXVII());
		JLabel epoqueLabel = new JLabel("Époque :", SwingConstants.CENTER);
		
		contentPane.add(epoqueLabel);
		frame.pack();
		//frame.setVisible(true);

    }

    class LocalListener implements ActionListener {
    	
    	private int command;
    	
        public LocalListener(int i) {
        	this.command = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}



	public void visible() {
		this.frame.setVisible(true);		
	}

}
