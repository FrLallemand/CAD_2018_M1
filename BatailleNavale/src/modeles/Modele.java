package modeles;

import java.util.Observable;

import modeles.bateaux.Bateau;

public class Modele extends Observable{
	public enum Joueur {J1, J2};
	public enum GameState { ENCOURS, DEMANDEBATEAUX, FIN};
	
	// Strategie des joueurs
	private Strategie strategieJ2;
	private Terrain terrainJ1, terrainJ2;
	
	private Joueur joueur;
	private GameState state;
	
	public Modele(Terrain tj1, Terrain tj2) {
		this.terrainJ1 = tj1;
		this.terrainJ2 = tj2;
		this.joueur = Joueur.J1;
		this.state = GameState.DEMANDEBATEAUX;
	}	
	
	public void run() {
		this.demandePlacementBateau(Joueur.J1);
		this.demandePlacementBateau(Joueur.J2);
		this.state = GameState.ENCOURS;
	}

	
	public void demandePlacementBateau(Joueur j) {
		if(j == Joueur.J1 && state == GameState.DEMANDEBATEAUX) {
			this.setChanged();
			this.notifyObservers();
		}
		if(j == Joueur.J2 && state == GameState.DEMANDEBATEAUX) {
			this.strategieJ2.placementBateaux(this.terrainJ2);
			this.setChanged();
			this.notifyObservers();
		}
	}
		
	public void placementAleatoire() {
		this.terrainJ1.placementFlotteHasard();
		state = GameState.ENCOURS;
		this.setChanged();
		this.notifyObservers();

	}
	
	public Bateau demandeChoixBateau(int numeroJoueur) {
		//TODO
		return null;		
	}
	
	public Position demandePositionTir(int numeroJoueur) {
		//TODO
		return null;		
	}
	
	public Terrain getTerrainJ1() {
		return terrainJ1;
	}

	public Terrain getTerrainJ2() {
		return terrainJ1;
	}

	public void setTerrainJ1(Terrain terrain) {
		this.terrainJ1 = terrain;
	}

	public void setTerrainJ2(Terrain terrain) {
		this.terrainJ2 = terrain;
	}
	
	public Strategie getStrategieJ2() {
		return strategieJ2;
	}
	public void setStrategieJ2(Strategie strategieJ2) {
		this.strategieJ2 = strategieJ2;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public GameState getState() {
		return state;
	}
	
	public void setState(GameState state) {
		this.state = state;
	}

}
