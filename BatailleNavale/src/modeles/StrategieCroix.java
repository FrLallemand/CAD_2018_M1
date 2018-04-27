package modeles;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import modeles.Strategie.NomsStrategies;
import modeles.bateaux.Bateau;

public class StrategieCroix extends Strategie{
	private LinkedList<Position> derniersTirs;
	private Random rand;

	public StrategieCroix(Terrain t) {
		super(t);
		this.derniersTirs = new LinkedList<>();
		rand = new Random();
		this.nomStrategie=NomsStrategies.CROIX;
	}

	@Override
	public Bateau choixBateauTir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position choixPositionTir() {
		Position p = new Position(0, 0);	
		

		//pas de tir, on prend au hasard
		int x = rand.nextInt(this.terrainAdversaire.getWidth());
		int y = rand.nextInt(this.terrainAdversaire.getHeight());
		p.setX(x);
		p.setY(y);
		if(derniersTirs.size() > 0) {
			// On regarde le dernier tir
			Position dernier = derniersTirs.pop();
			if(this.terrainAdversaire.estBateauTouche(dernier)) {
				// Le dernier coup a touché un bateau, on regarde si on a un autre coup
				Position avantDernier = derniersTirs.peekFirst();
				if(avantDernier != null) {
					derniersTirs.pop();
					if(this.terrainAdversaire.estBateauTouche(avantDernier)) {
						// on regarde la direction
						if((avantDernier.getX() == dernier.getX()-1) && (dernier.getX()+1 < this.terrain.getWidth())) {
							// on va continuer à tirer dans cette direction
							p.setY(dernier.getY());
							p.setX(dernier.getX()+1);							
						}
						else if((avantDernier.getX() == dernier.getX()+1) && (dernier.getX()-1 > 0)) {
							// on va continuer à tirer dans cette direction
							p.setY(dernier.getY());
							p.setX(dernier.getX()-1);							
						}							
						else if((avantDernier.getY() == dernier.getY()-1) && (dernier.getY()+1 < this.terrain.getHeight())) {
							// on va continuer à tirer dans cette direction
							p.setY(dernier.getY()+1);
							p.setX(dernier.getX());							
						}							
						else if((avantDernier.getY() == dernier.getY()+1) && (dernier.getY()-1 > 0)) {
							// on va continuer à tirer dans cette direction
							p.setY(dernier.getY()-1);
							p.setX(dernier.getX());							
						}
						derniersTirs.addFirst(dernier);
					}
				}
				else {
					boolean direction = rand.nextBoolean();
					if(direction) {
						if(dernier.getX()+1 < this.terrain.getWidth()) {
							p.setY(dernier.getY());
							p.setX(dernier.getX()+1);							
						}
						else if((dernier.getX()-1 > 0)) {
							p.setY(dernier.getY());
							p.setX(dernier.getX()-1);							
						}							
					} else {
						if(dernier.getY()+1 < this.terrain.getHeight()) {
							// on va continuer à tirer dans cette direction
							p.setY(dernier.getY()+1);
							p.setX(dernier.getX());							
						}							
						else if(dernier.getY()-1 > 0) {
							// on va continuer à tirer dans cette direction
							p.setY(dernier.getY()-1);
							p.setX(dernier.getX());							
						}
					}
					derniersTirs.addFirst(dernier);
				}
			}	
}
		
		derniersTirs.addFirst(p);
		//System.out.println(derniersTirs.length);
		return p;
	}

	@Override
	public void placementBateaux() {
		this.terrain.placementFlotteHasard();
	}

}
