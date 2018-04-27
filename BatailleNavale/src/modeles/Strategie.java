package modeles;

import modeles.bateaux.Bateau;
import modeles.Terrain;

public abstract class Strategie {
	
	public enum NomsStrategies { ALEATOIRE, CROIX };
	protected NomsStrategies nomStrategie;

	private int numeroJoueur;
	protected Terrain terrain, terrainAdversaire;
	

	public Strategie(Terrain t) {
		this.terrain = t;
	}
	
	public Terrain getTerrainAdversaire() {
		return terrainAdversaire;
	}
	
	public void setTerrainAdversaire(Terrain terrainAdversaire) {
		this.terrainAdversaire = terrainAdversaire;
	}
	
	public NomsStrategies getNomStrategie(){
		return this.nomStrategie;
	}

	public abstract void placementBateaux();
	public abstract Bateau choixBateauTir();	
	public abstract Position choixPositionTir();
}
