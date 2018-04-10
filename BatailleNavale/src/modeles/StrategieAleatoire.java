package modeles;

import modeles.bateaux.Bateau;
import modeles.Terrain;
import modeles.Position;
import java.util.Random;

public class StrategieAleatoire extends Strategie {
	Random rand = new Random();

	public void placementBateaux(){
		this.terrain.placementFlotteHasard();
	}

	public Bateau choixBateauTir(){
		return null;
	}

	public Position choixPositionTir(){
		int x = rand.nextInt(this.terrain.getWidth() + 1);
		int y = rand.nextInt(this.terrain.getHeight() + 1);
		Position p = new Position(x, y);
		return p;
	}
}
