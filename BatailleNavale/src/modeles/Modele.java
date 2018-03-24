package modeles;

import java.util.Observable;

public class Modele extends Observable{
	// Strategie des joueurs
	private Stategie stategieJ1, strategieJ2;
	private Terrain terrain;
	
	public Modele() {
		
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
		return stategieJ1;
	}
	public void setStategieJ1(Stategie stategieJ1) {
		this.stategieJ1 = stategieJ1;
	}
	public Stategie getStrategieJ2() {
		return strategieJ2;
	}
	public void setStrategieJ2(Stategie strategieJ2) {
		this.strategieJ2 = strategieJ2;
	}

}
