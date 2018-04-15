package modeles;

import java.util.Collection;

public abstract class Bateau {
	private String nom;
	private int munition;
	private int taille;
	private Collection<Integer> tableauVie;
	private Position position;
	private boolean direction;
	
	public abstract boolean estDetruit();	
	public abstract boolean testerTir(Position position);	
	public abstract void setPosition(Position position);	
	public abstract void effectuerTir(Position position);	

}
