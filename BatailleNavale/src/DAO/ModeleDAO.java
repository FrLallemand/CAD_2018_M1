package DAO;

import java.util.ArrayList;

import org.w3c.dom.Document;

import modeles.Flotte;
import modeles.GameFactory;
import modeles.Modele;
import modeles.Position;
import modeles.Strategie;
import modeles.StrategieChargement;
import modeles.Terrain;
import modeles.bateaux.Bateau;
import modeles.epoques.Epoque;
import modeles.epoques.Epoque.NomsEpoques;

public abstract class ModeleDAO {
	
	protected Modele m;
	public enum NomsDAO { XML};
	protected NomsDAO nomSav;

	public ModeleDAO(Modele m){
		this.m=m;
	}
	
	public abstract void sauvegarde(String file);
	
	public abstract void chargement(String file);
	
	
	protected void chargementModele(ArrayList<Position> bateauxJ1,ArrayList<Position> bateauxJ2, ArrayList<Position> tirsJ1, ArrayList<Position> tirsJ2,String epoqueJ1,String epoqueJ2, String strategie){
		Flotte fj1=new Flotte(new ArrayList<Bateau>(),getEpoque(epoqueJ1));
		Flotte fj2=new Flotte(new ArrayList<Bateau>(),getEpoque(epoqueJ2));
		m.getTerrainJ1().setFlotte(fj1);
		m.getTerrainJ2().setFlotte(fj2);
		m.getTerrainJ1().resetTerrain();
		m.getTerrainJ2().resetTerrain();
		m.setStrategieJ2(new StrategieChargement(tirsJ2,bateauxJ2));
		//partie bateaux
		for(int x=0;x<bateauxJ1.size();x++){			
			m.placement(bateauxJ2.get(x).getX(), bateauxJ2.get(x).getY(), bateauxJ2.get(x).getDirection(),false);
			m.placement(bateauxJ1.get(x).getX(), bateauxJ1.get(x).getY(), bateauxJ1.get(x).getDirection(),true);
		};
		//partie tirs
		for(int x=0;x<tirsJ1.size();x++){
			m.effectuerTir(tirsJ1.get(x));				
		}
		m.setStrategieJ2(getStrategie(strategie,m.getTerrainJ2()));
	}
	
	
	private Strategie getStrategie(String strategie,Terrain tj2){
		return GameFactory.getStrategie(strategie, tj2);
	}

	private Epoque getEpoque(String epoque){
		return GameFactory.getEpoque(epoque);
	}
	
	public NomsDAO getNom(){
		return this.nomSav;
	}
}
