package modeles.epoques;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import modeles.bateaux.Bateau;
import modeles.epoques.Epoque.NomsEpoques;

public abstract class Epoque {
	public enum NomsEpoques { XVII, MODERNE};
	private List<Supplier<Bateau>> bateauxEpoque;
	protected NomsEpoques nom;
	
	public NomsEpoques getNom(){
		return this.nom;
	}
	public void setBateauxEpoque() {
		Epoque e = this;

		bateauxEpoque = new ArrayList<Supplier<Bateau>>();
		bateauxEpoque.add(e::getBateauTaille5);
		bateauxEpoque.add(e::getBateauTaille4);
		bateauxEpoque.add(e::getBateauTaille3V1);
		bateauxEpoque.add(e::getBateauTaille3V2);
		bateauxEpoque.add(e::getBateauTaille2);
	}
	
	public Supplier<Bateau> getBateauxEpoque(int i) {
		return bateauxEpoque.get(i);
	}
	
	public int getBateauxEpoqueSize() {
		return bateauxEpoque.size();
	}
	
	public abstract Bateau getBateauTaille5();
	public abstract Bateau getBateauTaille4();
	public abstract Bateau getBateauTaille3V1();
	public abstract Bateau getBateauTaille3V2();
	public abstract Bateau getBateauTaille2();
}
