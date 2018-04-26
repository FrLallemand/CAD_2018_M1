package modeles;

import java.util.ArrayList;

import modeles.Strategie.NomsStrategies;
import modeles.bateaux.Bateau;
import modeles.epoques.Epoque;
import modeles.epoques.EpoqueModerne;
import modeles.epoques.EpoqueXVII;
import modeles.epoques.Epoque.NomsEpoques;

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
		e.setBateauxEpoque();
		Flotte flJ1 = new Flotte(new ArrayList<Bateau>(), e);
		Flotte flJ2 = new Flotte(new ArrayList<Bateau>(), e);
		Terrain tj1 = new Terrain(flJ1);
		Terrain tj2 = new Terrain(flJ2);

		Strategie sJ2;
		switch(strategieJ2) {
		case ALEATOIRE:
			sJ2 = new StrategieAleatoire(tj2);
			break;
		case CROIX:
			sJ2 = new StrategieCroix(tj2);
			break;
		default:
			sJ2 = new StrategieAleatoire(tj2);
			break;
		}

		Modele modele = new Modele(tj1, tj2);
		modele.setStrategieJ2(sJ2);
		return modele;
	}

	public static Strategie getStrategie(String strat,Terrain tj2){
		Strategie sJ2;
		Strategie.NomsStrategies strategieJ2 =Strategie.NomsStrategies.valueOf(strat);
		switch(strategieJ2) {
		case ALEATOIRE:
			sJ2 = new StrategieAleatoire(tj2);
			break;
		case CROIX:
			sJ2 = new StrategieCroix(tj2);
			break;
		default:
			sJ2 = new StrategieAleatoire(tj2);
			break;
		}
		return sJ2;
	}
	
	public static Epoque getEpoque(String strepoque){
		Epoque e;
		Epoque.NomsEpoques epoque =Epoque.NomsEpoques.valueOf(strepoque);
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
		return e;
	}
	
}
