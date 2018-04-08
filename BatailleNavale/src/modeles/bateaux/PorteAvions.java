package modeles.bateaux;

import modeles.Position;

public class PorteAvions extends Bateau{

	public PorteAvions() {
		super(5, "Porte-avions");
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
