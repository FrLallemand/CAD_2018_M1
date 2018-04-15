package modeles;

import modeles.Strategie.NomsStrategies;
import modeles.bateaux.Bateau;

public class StrategieCroix extends Strategie{

	public StrategieCroix(Terrain t) {
		super(t);
		// TODO Auto-generated constructor stub
		this.nomStrategie=NomsStrategies.CROIX;
	}

	@Override
	public Bateau choixBateauTir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position choixPositionTir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placementBateaux() {
	}

}
