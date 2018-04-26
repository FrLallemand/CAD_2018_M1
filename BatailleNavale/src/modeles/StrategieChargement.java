package modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modeles.Strategie.NomsStrategies;
import modeles.bateaux.Bateau;

public class StrategieChargement extends Strategie {
	
	ArrayList<Position> tir;
	ArrayList<Position> bateaux;
	
	public StrategieChargement(Terrain t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	public StrategieChargement(ArrayList<Position> tir, ArrayList<Position> bateaux) {
		super(null);
		this.tir=tir;
		this.bateaux=bateaux;
	}

	public Bateau choixBateauTir(){
		return null;
	}

	public Position choixPositionTir(){
		Position p=tir.get(0);
		tir.remove(p);
		return p;
	}

	@Override
	public void placementBateaux() {
		while(!this.terrain.getFlotte().placementFini()){
			Position p=bateaux.get(0);
			bateaux.remove(p);
			this.terrain.placementFlotte(p);
		}
	}
}
