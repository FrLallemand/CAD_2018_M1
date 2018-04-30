package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modeles.BatailleNavale;
import modeles.Modele;
import modeles.Strategie.NomsStrategies;
import modeles.epoques.Epoque;
import modeles.epoques.Epoque.NomsEpoques;
import vues.ChoixOptions;

public class OptionsControleur implements ActionListener{
	
	private ChoixOptions vue;
	private BatailleNavale modele;
	
	public OptionsControleur(ChoixOptions co, BatailleNavale modele) {
		this.vue = co;
		this.modele = modele;
	}
	
	public String getEpoque() {
		return vue.getEpoques().getSelection().getActionCommand();
	}
	
	public String getStrategie() {
		return vue.getStrategies().getSelection().getActionCommand();
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		this.vue.destroy();
		modele.game(getEpoque(), getStrategie());
    }

}
