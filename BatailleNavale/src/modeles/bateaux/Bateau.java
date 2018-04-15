package modeles.bateaux;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import modeles.Position;
import modeles.Position.Direction;
import modeles.bateaux.Bateau;

public class Bateau {
	private String nom;
	private int munition;
	private int taille;
	private List<Integer> tableauVie;
	private Position position;
	
	public Bateau(int t, String n) {
		tableauVie = new ArrayList<>();
		for(int i = 0; i < t; i++) {
			tableauVie.add(1);
		}
		this.taille = t;
		this.nom = n;
	}
	
	public boolean estDetruit() {
		for(int case_vie : tableauVie) {
			if(case_vie > 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean testerTir(Position tir) {
		for(int i = 0; i < this.taille; i++) {
			if(position.getDirection() == Direction.HORIZONTAL) {
				if(position.getX() + i == tir.getX() && position.getY() == tir.getY()) {
					return true;
				}
			}
			else {
				if(position.getX() == tir.getX() && position.getY() + i == tir.getY()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void effectuerTir(Position tir) {
		for(int i = 0; i < this.taille; i++) {
			if(position.getDirection() == Direction.HORIZONTAL) {
				if(position.getX() + i == tir.getX() && position.getY() == tir.getY()) {
					this.tableauVie.set(i, this.tableauVie.get(i) - 1);
				}
			}
			else {
				if(position.getX() == tir.getX() && position.getY() + i == tir.getY()) {
					this.tableauVie.set(i, this.tableauVie.get(i) - 1);
				}
			}
		}
	}

	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}

	public Position getPosition(){
		return this.position;
	}

	public String getNom(){
		return this.nom;
	}
	
	public boolean equals(Object o){
		Bateau b = (Bateau) o;
		return (b.getPosition().getX() == this.getPosition().getX() && b.getPosition().getY() == this.getPosition().getY());
 	}
	
}
