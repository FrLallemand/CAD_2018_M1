import modeles.BatailleNavale;
import vues.ChoixOptions;
import vues.VuePrincipale;

public class Main {

	public static void main(String[] args) {
		BatailleNavale bn = new BatailleNavale();
		ChoixOptions choixOptions = new ChoixOptions(bn);
		bn.addObserver(choixOptions);
		choixOptions.visible();
		//bn.game();
		
		//VueTerrain vt=new VueTerrain();
	}

}
