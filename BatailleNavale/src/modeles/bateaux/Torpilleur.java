package modeles.bateaux;

import modeles.Position;

public class Torpilleur extends Bateau{

	public Torpilleur() {
		super(2, "Torpilleur");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean estDetruit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean testerTir(Position position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effectuerTir(Position position) {
		// TODO Auto-generated method stub
		
	}

}
