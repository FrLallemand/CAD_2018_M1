package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modeles.BatailleNavale;
import modeles.Strategie.NomsStrategies;
import modeles.epoques.Epoque.NomsEpoques;
import vues.ChoixOptions;
import vues.VuePrincipale;

public class NouvellePartieControleur implements ActionListener {

	private VuePrincipale vue;
	
	public NouvellePartieControleur(VuePrincipale v) {
		this.vue = v;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		vue.destroy();
		BatailleNavale bn = new BatailleNavale();
		ChoixOptions choixOptions = new ChoixOptions(bn);
		bn.addObserver(choixOptions);
		choixOptions.visible();
    }

}
