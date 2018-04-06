package modeles;

import java.util.Observable;

public class Modele extends Observable{
	// Strategie des joueurs
	private Stategie strategieJ1, strategieJ2;
	private Terrain terrain;
	
	
	public Modele(Terrain t) {
		this.terrain = t;
	}	
	
	public void run() {
	}
	
	public Flotte demandePlacementBateau(int numeroJoueur, Bateau bateau) {
		//TODO
		return null;		
	}

	public Bateau demandeChoixBateau(int numeroJoueur) {
		//TODO
		return null;		
	}
	
	public Position demandePositionTir(int numeroJoueur) {
		//TODO
		return null;		
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	public Stategie getStategieJ1() {
		return strategieJ1;
	}
	public void setStategieJ1(Stategie stategieJ1) {
	}
	public Stategie getStrategieJ2() {
		return strategieJ2;
	}
	public void setStrategieJ2(Stategie strategieJ2) {
		this.strategieJ2 = strategieJ2;
	}

}
