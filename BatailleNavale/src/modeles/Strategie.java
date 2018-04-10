package modeles;

import modeles.bateaux.Bateau;
import modeles.Terrain;

public abstract class Strategie {
	
	public enum NomsStrategies { ALEATOIRE, CROIX };

	private int numeroJoueur;
	
	public Strategie() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void placementBateaux(Terrain t);
	public abstract Bateau choixBateauTir();	
	public abstract Position choixPositionTir();
}
