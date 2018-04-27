package modeles.epoques;

import modeles.bateaux.Bateau;
import modeles.bateaux.EpoqueXVII.Corvette;
import modeles.bateaux.EpoqueXVII.Fregate;
import modeles.bateaux.EpoqueXVII.Gallion;
import modeles.bateaux.EpoqueXVII.Goelette;

public class EpoqueXVII extends Epoque{
	
	public EpoqueXVII(){
		this.nom=NomsEpoques.XVII;
	}
	
	@Override
	public Bateau getBateauTaille5() {
		return new Gallion();
	}

	@Override
	public Bateau getBateauTaille4() {
		return new Fregate();
	}

	@Override
	public Bateau getBateauTaille3V1() {
		return new Goelette();
	}

	@Override
	public Bateau getBateauTaille3V2() {
		// On choisi de ne pas mettre de deuxime bateau de taille 3 pour cette époque
		return null;
	}

	@Override
	public Bateau getBateauTaille2() {
		return new Corvette();
	}


}
