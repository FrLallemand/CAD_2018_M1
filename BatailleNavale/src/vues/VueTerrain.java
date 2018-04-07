package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

public class VueTerrain  implements Observer{

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
	



	public VueTerrain(){
		//init JFrame
		affichage=new JFrame("Bataille Navale");
		affichage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		affichage.setLayout(new BorderLayout());
		affichage.setSize(1000, 600);
		Container contaff=affichage.getContentPane();
		//affichage partie joueur 1(joueur humain)
		playerView=new JPanel();
		playerView.setLayout(new BorderLayout());
		playerView.add(new JLabel("Joueur 1",SwingConstants.CENTER),BorderLayout.NORTH);
		playerViewTerrain=new JPanel();
		playerViewTerrain.setLayout(new GridLayout(11,11));
		this.fillGrid(playerViewTerrain,true);
		playerView.add(playerViewTerrain,BorderLayout.CENTER);
		contaff.add(playerView,BorderLayout.WEST);
		//affichage partie joueur 2(joueur IA)
		ennemyView=new JPanel();
		ennemyView.setLayout(new BorderLayout());
		ennemyView.add(new JLabel("Joueur 2",SwingConstants.CENTER),BorderLayout.NORTH);
		ennemyViewTerrain=new JPanel();
		ennemyViewTerrain.setLayout(new GridLayout(11,11));
		this.fillGrid(ennemyViewTerrain,false);
		ennemyView.add(ennemyViewTerrain,BorderLayout.CENTER);
		contaff.add(ennemyView,BorderLayout.EAST);
		//affichage menu/option/information supplémentaires
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
		contaff.add(optionInfoTop,BorderLayout.NORTH);
		affichage.setVisible(true);
	}

	// Remplis la grille de chaque joueurs
	private void fillGrid(JPanel vue, boolean isplayerView){
		String[] lettres={"A","B","C","D","E","F","G","H","I","J"};
		JButton temp;
		vue.add(new JLabel(" ",SwingConstants.CENTER));
		for(int x=1;x<11;x++){
			vue.add(new JLabel(""+x,SwingConstants.CENTER));
		}

		for(int x=0;x<10;x++){
			vue.add(new JLabel(lettres[x],SwingConstants.CENTER));
			for(int y=0;y<10;y++){
				temp=new JButton();
				temp.setBackground(Color.BLUE);
				temp.setEnabled(false);
				vue.add(temp);
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
