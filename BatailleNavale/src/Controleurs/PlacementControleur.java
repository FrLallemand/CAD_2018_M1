package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modeles.Modele;

public class PlacementControleur implements ActionListener{
	
	private Modele modele;
	
	public PlacementControleur(Modele m) {
		this.modele = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.modele.placementAleatoire();
	}
	

}
