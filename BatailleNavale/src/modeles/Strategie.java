package modeles;

public abstract class Strategie {
	
	public enum NomsStrategies { ALEATOIRE, CROIX};

	private int numeroJoueur;
	
	public Strategie() {
		// TODO Auto-generated constructor stub
	}
	
	public void placementBateaux() {
		
	}
	
	public Bateau choixBateauTir() {
		//TODO
		return null;
	}
	
	public Position choixPositionTir() {
		//TODO
		return null;
	}

}
