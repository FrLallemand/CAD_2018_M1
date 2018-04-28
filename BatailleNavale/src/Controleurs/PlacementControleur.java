package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import modeles.Modele;
import modeles.Position.Direction;

public class PlacementControleur implements ActionListener{
	
	private Modele modele;
	private boolean alea;
	private int x,y;
	private JComboBox orientation;
	
	public PlacementControleur(Modele m) {
		this.modele = m;
		this.alea=true;
	}
	
	public PlacementControleur(Modele m, int x, int y, JComboBox orientation) {
		this.modele = m;
		this.alea=false;
		this.x=x;
		this.y=y;
		this.orientation=orientation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(alea){
			this.modele.placementAleatoire();
		}else{
			//System.out.println(x+ " "+ y);
			if(orientation.getSelectedIndex()==0){
				this.modele.placement(x, y, Direction.HORIZONTAL,true);
			}else{
				this.modele.placement(x, y, Direction.VERTICAL,true);				
			}
		}
	}
	

}
