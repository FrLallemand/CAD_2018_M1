package modeles;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import modeles.Position.Direction;
import modeles.bateaux.Bateau;
import modeles.epoques.Epoque;

public class Flotte implements Iterable<Bateau>{

	private List<Bateau> bateaux;
	private LinkedList<Bateau> bateauxAPlacer;
	private Epoque epoque;
//	private int bateauxAPlacer;

	public Flotte(List<Bateau> liste_origine, Epoque e) {
		this.epoque = e;
		this.bateauxAPlacer = new LinkedList<Bateau>();
		this.bateaux = liste_origine;
		for(int i=0; i<epoque.getBateauxEpoqueSize(); i++) {
			Bateau b = epoque.getBateauxEpoque(i).get();
			if(b != null) {
				bateauxAPlacer.add(epoque.getBateauxEpoque(i).get());
			}
		}
		//bateauxAPlacer = bateaux.size();
	}

	// probablement inutile
	public boolean testerTir(Position position) {
		// TODO Auto-generated method stub
		return false;
	}

	public Bateau bateauSuivantAPlacer() {
		return bateauxAPlacer.getFirst();
	}

	public void placerBateauSuivant(Position p) {
		if(!placementFini()) {
			Bateau b = bateauxAPlacer.pop();
			b.setPosition(p);
			bateaux.add(b);
		}
	}

	public boolean placementFini() {
		return this.bateauxAPlacer.size() == 0;
	}

	
	public boolean estDetruit(Bateau b) {
		return false;
	}

	public boolean flotteDetruite() {
		for(Bateau b : this.bateaux){
			if(!b.estDetruit()){
				return false;
			}
		}

		return true;
	}

	public ResultatTir effectuerTir(Position tir) {
		for(Bateau b : this.bateaux) {
			if(b.testerTir(tir)) {
				b.effectuerTir(tir);
				if(b.estDetruit()) {
					return ResultatTir.COULE;
				}
				else {
					return ResultatTir.TOUCHE;
				}
			}
		}
		
		return ResultatTir.EAU;
	}

	public Bateau getBateau(Position p) {
		for(Bateau b : this.bateaux) {
			if(b.testerTir(p)) {
				return b;
			}
		}
		
		return null;
	}
	
	public Optional<Bateau> bateauPresentAPosition(Position position){
		// TODO
		return Optional.empty();		
	}

	@Override
	public Iterator<Bateau> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Epoque getEpoque() {
		return epoque;
	}

	public void setEpoque(Epoque epoque) {
		this.epoque = epoque;
	}
	
	public Bateau getBateauParID(int id){
		return this.bateaux.get(id);
	}
	
	public int getNbBateau(){
		return this.bateaux.size();
	}
	
}
