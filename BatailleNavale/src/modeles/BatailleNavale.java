package modeles;

import java.util.Observable;

import vues.ChoixOptions;

public class BatailleNavale extends Observable{
	Modele modele;
	
	public void game() {
		GameFactory factory = new GameFactory();
		// TODO gui (vue) pour le choix des époques, mode de tir, strategie.
		//this.modele = factory.getNewGame(epoque, modeTir, strategieJ1, strategieJ2);
		//this.modele.run();
		
	}
	
}
