package modeles;

import java.util.ArrayList;

import modeles.Epoque.NomsEpoques;
import modeles.Strategie.NomsStrategies;

public class GameFactory {
	
	public Modele getNewGame(NomsEpoques epoque, int modeTir, int strategieJ1, NomsStrategies strategieJ2) {
		Epoque e;
		switch(epoque) {
		case MODERNE:
			e = new EpoqueModerne();
			break;
		case XVII:
			e = new EpoqueXVII();
			break;
		default:
			e = new EpoqueModerne();
			break;
		}
		
		Strategie sJ2;
		switch(strategieJ2) {
		case ALEATOIRE:
			sJ2 = new StrategieAleatoire();
			break;
		case CROIX:
			sJ2 = new StrategieCroix();
			break;
		default:
			sJ2 = new StrategieAleatoire();
			break;
		}

		Flotte flJ1 = new Flotte(new ArrayList<Bateau>());
		flJ1.setEpoque(e);
		Flotte flJ2 = new Flotte(new ArrayList<Bateau>());
		flJ2.setEpoque(e);
		Terrain tj1 = new Terrain(flJ1);
		Terrain tj2 = new Terrain(flJ2);
		Modele modele = new Modele(tj1, tj2);
		modele.setStrategieJ1(new StrategieJoueur());
		modele.setStrategieJ2(sJ2);
		return modele;
	}
}
