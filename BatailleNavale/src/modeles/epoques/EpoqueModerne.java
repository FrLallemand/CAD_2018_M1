package modeles.epoques;

import modeles.bateaux.Bateau;
import modeles.bateaux.ContreTorpilleur;
import modeles.bateaux.Croiseur;
import modeles.bateaux.PorteAvions;
import modeles.bateaux.SousMarin;
import modeles.bateaux.Torpilleur;

public class EpoqueModerne extends Epoque {

	public EpoqueModerne(){
		this.nom=NomsEpoques.MODERNE;
	}

	@Override
	public Bateau getBateauTaille5() {
		return new PorteAvions();
	}

	@Override
	public Bateau getBateauTaille4() {
		return new Croiseur();
	}

	@Override
	public Bateau getBateauTaille3V1() {
		return new ContreTorpilleur();
	}

	@Override
	public Bateau getBateauTaille3V2() {
		return new SousMarin();
	}

	@Override
	public Bateau getBateauTaille2() {
		return new Torpilleur();
	}
}
