package modeles;

import java.util.ArrayList;

public class GameFactory {
	
	public Modele getNewGame(int epoque, int modeTir, int strategieJ1, int strategieJ2) {
		Flotte flJ1 = new Flotte(new ArrayList<Bateau>());
		Flotte flJ2 = new Flotte(new ArrayList<Bateau>());
		Terrain t = new Terrain(flJ1, flJ2);
		Modele modele = new Modele(t);
		return modele;
	}
}
