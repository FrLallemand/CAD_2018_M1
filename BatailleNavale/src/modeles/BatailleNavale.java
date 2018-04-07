package modeles;

import java.util.Observable;

import modeles.Epoque.NomsEpoques;
import modeles.Strategie.NomsStrategies;
import vues.ChoixOptions;

public class BatailleNavale extends Observable{
	Modele modele;
	
	public BatailleNavale() {
		
	}
	
	public void game(NomsEpoques epoque, NomsStrategies strategieJ2) {
		GameFactory factory = new GameFactory();
		int modeTir = 0;
		int strategieJ1 = 0;
		// TODO gui (vue) pour le choix des époques, mode de tir, strategie.
		this.modele = factory.getNewGame(epoque, modeTir, strategieJ1, strategieJ2);
		this.modele.run();
		
	}
	
}
