package modeles;

import modeles.bateaux.Bateau;
import modeles.Terrain;

public class StrategieAleatoire extends Strategie {
	public void placementBateaux(Terrain t){
		t.placementFlotteHasard();
	}

	public Bateau choixBateauTir(){
		return null;
	}

	public Position choixPositionTir(){
		return null;
	}
}
