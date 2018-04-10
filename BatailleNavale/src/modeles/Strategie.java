package modeles;

import modeles.bateaux.Bateau;
import modeles.Terrain;

public abstract class Strategie {
	
	public enum NomsStrategies { ALEATOIRE, CROIX };

	private int numeroJoueur;
	private Terrain terrain;
	
	public Strategie(Terrain t) {
		this.terrain = t;
	}
	
	public abstract void placementBateaux();
	public abstract Bateau choixBateauTir();	
	public abstract Position choixPositionTir();
}
