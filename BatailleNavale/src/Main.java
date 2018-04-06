import modeles.BatailleNavale;
import vues.ChoixOptions;

public class Main {

	public static void main(String[] args) {
		BatailleNavale bn = new BatailleNavale();
		ChoixOptions choixOptions = new ChoixOptions(bn);
		bn.addObserver(choixOptions);
		choixOptions.visible();
		//bn.game();
	}

}
