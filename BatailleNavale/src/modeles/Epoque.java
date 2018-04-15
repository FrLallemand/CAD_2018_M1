package modeles;

public abstract class Epoque {
	public enum NomsEpoques { XVII, MODERNE}
	protected NomsEpoques nom;
	
	public NomsEpoques getNom(){
		return this.nom;
	}
}
