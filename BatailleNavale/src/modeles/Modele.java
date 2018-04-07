package modeles;

import java.util.Observable;

public class Modele extends Observable{
	// Strategie des joueurs
	private Strategie strategieJ1, strategieJ2;
	private Terrain terrain;
	
	
	public Modele(Terrain t) {
		this.terrain = t;
	}	
	
	public void run() {
		System.out.println("TODO");
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
	public Strategie getStrategieJ1() {
		return strategieJ1;
	}
	public void setStrategieJ1(Strategie strategieJ1) {
	}
	public Strategie getStrategieJ2() {
		return strategieJ2;
	}
	public void setStrategieJ2(Strategie strategieJ2) {
		this.strategieJ2 = strategieJ2;
	}

}
