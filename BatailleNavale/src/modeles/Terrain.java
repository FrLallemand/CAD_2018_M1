package modeles;

import java.lang.Thread.State;
import java.util.Random;

import Exception.PopUpException;
import modeles.Position.Direction;
import modeles.bateaux.Bateau;

public class Terrain {
	private Flotte flotte;
	private int width, height;
	public enum StatusCase{EAU, COULE, BATEAU, EAUTOUCHE, BATEAUTOUCHE};
	private StatusCase cases[][];
	Random rand;

	public boolean estEau(Position p) {
		if(cases[p.getY()][p.getX()] == StatusCase.EAU)
			return true;
		return false;
	}	

	public boolean estCoule(Position p) {
		if(cases[p.getY()][p.getX()] == StatusCase.COULE)
			return true;
		return false;
	}	

	public boolean estEauTouche(Position p) {
		if(cases[p.getY()][p.getX()] == StatusCase.EAUTOUCHE)
			return true;
		return false;
	}	

	public boolean estBateauTouche(Position p) {
		if(cases[p.getY()][p.getX()] == StatusCase.BATEAUTOUCHE)
			return true;
		return false;
	}	

	public StatusCase[][] getCases() {
		return cases;
	}

	public void setCases(StatusCase[][] cases) {
		this.cases = cases;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Terrain(Flotte flJ1) {
		this.setFlotte(flJ1);	
		this.width = 10;
		this.height = 10;
		this.cases = new StatusCase[height][width];
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
				cases[i][j] = StatusCase.EAU;

		this.rand = new Random();
	}

	public boolean effectuerTir(Position position) {
		if(testerTir(position)) {
			switch (flotte.effectuerTir(position)) {
			case TOUCHE:
				cases[position.getY()][position.getX()] = StatusCase.BATEAUTOUCHE;
				break;
			case EAU:
				cases[position.getY()][position.getX()] = StatusCase.EAUTOUCHE;				
				break;
			case COULE:
				Bateau b = this.flotte.getBateau(position);
				int x = b.getPosition().getX();
				int y = b.getPosition().getY();
				int taille = b.getTaille();

				for(int i = 0; i < taille; i++) {
					if(b.getPosition().getDirection() == Direction.HORIZONTAL) {
						this.cases[y][x+i] = StatusCase.COULE;
					}
					else {
						this.cases[y+i][x] = StatusCase.COULE;
					}
				}
				break;
			default:
				break;
			}
			return true;
		} else {			
			return false;
		}
	}

	public boolean testerTir(Position position) {
		if((position.getX() < this.width) && (position.getY() < this.height)) {
			return true;
		}
		return false;
	}

	public Flotte getFlotte() {
		return flotte;
	}

	public void setFlotte(Flotte flotteJ1) {
		this.flotte = flotteJ1;
	}

	public void placementFlotteHasard() {
		while(!flotte.placementFini()) {
			Bateau b = suivant();
			boolean placementValide = false;
			while(!placementValide) {
				int x = rand.nextInt(this.width + 1);
				int y = rand.nextInt(this.height+ 1);
				boolean d = rand.nextBoolean();
				Direction dir;
				if(d) {
					dir = Direction.HORIZONTAL;
				} else {
					dir = Direction.VERTICAL;					
				}
				Position p = new Position(x, y, dir);

				placementValide = placementValide(p, b);
				if(placementValide) {
					placerSurTerrain(b, p);
				}
			}
		}
	}

	public boolean placementFlotte(Position p) {
		boolean placementValide=false;
		if(!flotte.placementFini()) {
			Bateau b = suivant();
			placementValide = placementValide(p, b);
			try{
				if(placementValide) {
					placerSurTerrain(b, p);
				}else{
					throw new PopUpException("impossible de placer le bateau ici.");
				}
			}catch(PopUpException e){
				//e.printStackTrace();
			}
		}
		return placementValide;
	}


	public String toString() {
		String s = new String();
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				switch (cases[i][j]) {
				case BATEAU:
					//System.out.print("X");
					s += "X";
					break;
				case EAU:
					//System.out.print(" ");					
					s += " ";
					break;
				case BATEAUTOUCHE:
					s += "#";
					break;
				case EAUTOUCHE:
					s += "O";
					break;
				case COULE:
					s += "C";
					break;
				default:
					break;
				}
			}
			s += "\n";
			//System.out.print("\n");
		}
		return s;
	}
	public void placerSurTerrain(Bateau b, Position p) {
		flotte.placerBateauSuivant(p);
		int x = p.getX();
		int y = p.getY();		
		switch (p.getDirection()) {
		case HORIZONTAL:
			for(int i=x; i< x+b.getTaille(); i++) {
				cases[y][i] = StatusCase.BATEAU;
			}			
			break;
		case VERTICAL:
			for(int i=y; i< y+b.getTaille(); i++) {
				cases[i][x] = StatusCase.BATEAU;
			}						
			break;
		default:
			break;
		}
	}


	public boolean placementValide(Position p, Bateau b) {
		boolean valide = false;
		// bonnes coordonnées ?
		int x = p.getX();
		int y = p.getY();
		//if(b.getPosition().getDirection() == Direction.HORIZONTAL) {
			
		if((x >= 0 && x < this.width) && 
				(y >= 0 && y < this.height)) {
			int taille = b.getTaille();
			switch (p.getDirection()) {
			case HORIZONTAL:
				if(x + taille <= this.width) {
					for(int i=x; i< x+taille; i++) {
						if(cases[y][i] != StatusCase.EAU) {
							valide = false;
							break;
						} else {
							valide = true;
						}
					}
				}
				break;
			case VERTICAL:
				if(y + taille < this.height) {
					for(int i=y; i<= y+taille; i++) {
						if(cases[i][x] != StatusCase.EAU) {
							valide = false;
							break;
						} else {
							valide = true;
						}
					}
				}
				break;
			default:
				break;
			}
		}
		return valide;
	}
	
	public Bateau suivant() {
		return flotte.bateauSuivantAPlacer();
	}

}
