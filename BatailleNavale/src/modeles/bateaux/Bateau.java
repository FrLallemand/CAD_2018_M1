package modeles.bateaux;

import java.util.Collection;

import modeles.Position;

public abstract class Bateau {
	private String nom;
	private int munition;
	private int taille;
	private Collection<Integer> tableauVie;
	private Position position;
	private boolean direction;
	
	public Bateau(int t, String n) {
		this.setTaille(t);
		this.nom = n;
	}
	public abstract boolean estDetruit();	
	public abstract boolean testerTir(Position position);	
	public abstract void setPosition(Position position);
	public abstract void effectuerTir(Position position);

	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}	

}