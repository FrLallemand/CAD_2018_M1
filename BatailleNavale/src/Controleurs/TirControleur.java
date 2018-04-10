package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modeles.Modele;
import modeles.Position;

public class TirControleur implements ActionListener{
	private Modele modele;
	private int posX;
	private int posY;
	
	public TirControleur(Modele m, int w, int h) {
		this.modele = m;
		this.posX = w;
		this.posY = h;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.modele.effectuerTir(new Position(posX, posY));
	}

}
