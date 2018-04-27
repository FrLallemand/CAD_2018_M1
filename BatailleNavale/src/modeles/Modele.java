package modeles;

import java.util.Observable;

import javax.print.attribute.standard.JobHoldUntil;

import modeles.Position.Direction;
import modeles.bateaux.Bateau;

public class Modele extends Observable{
	public enum Joueur {J1, J2};
	public enum GameState { ENCOURS, ECHECTIR, DEMANDEBATEAUX, FIN };
	
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
		this.state = GameState.DEMANDEBATEAUX;
		this.demandePlacementBateau(Joueur.J1);
		this.demandePlacementBateau(Joueur.J2);
	}

	
	public void demandePlacementBateau(Joueur j) {
		if(j == Joueur.J1 && state == GameState.DEMANDEBATEAUX) {
			this.setChanged();
			this.notifyObservers();
		}
		if(j == Joueur.J2 && state == GameState.DEMANDEBATEAUX) {
			this.strategieJ2.placementBateaux();
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
	
	public void placementjoueur(int x, int y, Direction dir) {
		this.terrainJ1.placementFlotte(new Position(x,y,dir));
		if(this.terrainJ1.getFlotte().placementFini()){
			state = GameState.ENCOURS;
		}
		this.setChanged();
		this.notifyObservers();

	}
	
	public Bateau demandeChoixBateau() {
		return this.terrainJ1.suivant();		
	}
	
	public Position demandePositionTir(int numeroJoueur) {
		//TODO
		return null;		
	}
	
	//public void 
	
	public Terrain getTerrainJ1() {
		return terrainJ1;
	}

	public Terrain getTerrainJ2() {
		return terrainJ2;
	}

	public void setTerrainJ1(Terrain terrain) {
		this.terrainJ1 = terrain;
		this.setChanged();
		this.notifyObservers();
	}

	public void setTerrainJ2(Terrain terrain) {
		this.terrainJ2 = terrain;
		this.setChanged();
		this.notifyObservers();
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
	
	public Joueur getJoueurGagnant() {
		if(this.terrainJ1.getFlotte().flotteDetruite()) {
			return this.joueur.J2;
		}
		else if(this.terrainJ2.getFlotte().flotteDetruite()) {
			return this.joueur.J1;
		}
		
		return this.joueur.J1;
	}
	
	public void effectuerTir(Position p) {
		if(this.joueur == Joueur.J1 && this.state != GameState.FIN) {	
			if(this.terrainJ2.effectuerTir(p)) {
				this.state = GameState.ENCOURS;
				this.joueur = Joueur.J2;
			} else {
				this.state = GameState.ECHECTIR;
				this.joueur = Joueur.J1;				
			}	
			
			this.setChanged();
			this.notifyObservers();
			
			Position tir = strategieJ2.choixPositionTir();
			this.terrainJ1.effectuerTir(tir);
			this.joueur = Joueur.J1;
			this.state = GameState.ENCOURS;
			
			this.setChanged();
			this.notifyObservers();
		}
		if(this.terrainJ1.getFlotte().flotteDetruite() || this.terrainJ2.getFlotte().flotteDetruite() ) {
			this.state = GameState.FIN;
			this.setChanged();
			this.notifyObservers();
		}
	}

}
