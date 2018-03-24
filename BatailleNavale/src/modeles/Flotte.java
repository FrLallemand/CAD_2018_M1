package modeles;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Flotte implements Iterable<Bateau>{

	private List<Bateau> bateaux;

	public Flotte(List<Bateau> liste_origine) {
		this.bateaux = liste_origine;
	}
	
	public Boolean flotteDetruite() {
		// TODO
		return true;
	}
	
	public ResultatTir effectuerTir(Position position) {
		// TODO
		return ResultatTir.EAU;
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


}
