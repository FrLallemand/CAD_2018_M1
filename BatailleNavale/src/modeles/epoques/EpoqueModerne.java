package modeles.epoques;

import modeles.bateaux.Bateau;
import modeles.bateaux.EpoqueModerne.ContreTorpilleur;
import modeles.bateaux.EpoqueModerne.Croiseur;
import modeles.bateaux.EpoqueModerne.PorteAvions;
import modeles.bateaux.EpoqueModerne.SousMarin;
import modeles.bateaux.EpoqueModerne.Torpilleur;

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
