package modeles;

import java.util.ArrayList;

import DAO.ModeleDAO;
import DAO.ModeleDAO_XML;
import modeles.Strategie.NomsStrategies;
import modeles.bateaux.Bateau;
import modeles.epoques.Epoque;
import modeles.epoques.EpoqueModerne;
import modeles.epoques.EpoqueXVII;
import modeles.epoques.Epoque.NomsEpoques;

public class GameFactory {

	public Modele getNewGame(String epoque, int modeTir, int strategieJ1, String strategieJ2) {
		Epoque e;
		e=GameFactory.getEpoque(epoque);
		Flotte flJ1 = new Flotte(new ArrayList<Bateau>(), e);
		Flotte flJ2 = new Flotte(new ArrayList<Bateau>(), e);
		Terrain tj1 = new Terrain(flJ1);
		Terrain tj2 = new Terrain(flJ2);

		Strategie sJ2=getStrategie(strategieJ2,tj1,tj2);
		Modele modele = new Modele(tj1, tj2);
		modele.setStrategieJ2(sJ2);
		return modele;
	}

	public static Strategie getStrategie(String strat,Terrain tj1,Terrain tj2){
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
		sJ2.setTerrainAdversaire(tj1);
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
		e.setBateauxEpoque();
		return e;
	}
	
	public static ModeleDAO getModeleDAO(String strdao,Modele m){
		ModeleDAO mdao;
		ModeleDAO.NomsDAO dao =ModeleDAO.NomsDAO.valueOf(strdao);
		switch(dao) {
		case XML:
			mdao = new ModeleDAO_XML(m);
			break;
		default:
			mdao = new ModeleDAO_XML(m);
			break;
		}
		return mdao;
	}
	
}
