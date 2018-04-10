package modeles;

import java.lang.Thread.State;
import java.util.Random;

import modeles.Position.Direction;
import modeles.bateaux.Bateau;

public class Terrain {
	private Flotte flotteJ1, flotteJ2;
	private int width, height;
	public enum StatusCase{EAU, BATEAU, EAUTOUCHE, BATEAUTOUCHE};
	private StatusCase cases[][];
			
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
		//this.setFlotteJ2(flJ2);		
		this.width = 10;
		this.height = 10;
		this.cases = new StatusCase[width][height];
		for(int i=0; i<width; i++)
			for(int j=0; j<height; j++)
				cases[i][j] = StatusCase.EAU;
	}
	
	public void effectuerTir(int numeroJoueur, Position position) {
		
	}

	public boolean testerTir(int numeroJoueur, Position position) {
		return false;
	}

	public Flotte getFlotteJ2() {
		return flotteJ2;
	}

	public void setFlotteJ2(Flotte flotteJ2) {
		this.flotteJ2 = flotteJ2;
	}

	public Flotte getFlotte() {
		return flotteJ1;
	}

	public void setFlotte(Flotte flotteJ1) {
		this.flotteJ1 = flotteJ1;
	}

	public void placementFlotteHasard() {
		Random rand = new Random();
		while(!flotteJ1.placementFini()) {
			Bateau b = flotteJ1.bateauSuivantAPlacer();
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
		//System.out.println(this);
	}
	
	
	public String toString() {
		String s = new String();
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				switch (cases[i][j]) {
				case BATEAU:
					//System.out.print("X");
					s += "X";
					break;
				case EAU:
					//System.out.print(" ");					
					s += " ";
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
		flotteJ1.placerBateauSuivant(p);
		int x = p.getX();
		int y = p.getY();		
		switch (p.getDirection()) {
		case HORIZONTAL:
			for(int i=x; i< x+b.getTaille(); i++) {
				cases[i][y] = StatusCase.BATEAU;
			}			
			break;
		case VERTICAL:
			for(int i=y; i< y+ b.getTaille(); i++) {
				cases[x][i] = StatusCase.BATEAU;
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
		if((x >= 0 && x < this.width) && 
			(y >= 0 && y < this.height)) {
				int taille = b.getTaille();
				switch (p.getDirection()) {
				case HORIZONTAL:
					if(x + taille <= this.width) {
						for(int i=x; i<width; i++) {
							if(cases[i][y] != StatusCase.EAU) {
								valide = false;
								break;
							} else {
								valide = true;
							}
						}
					}
					break;
				case VERTICAL:
					if(y + taille <= this.height) {
						for(int i=y; i<height; i++) {
							if(cases[x][i] != StatusCase.EAU) {
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
			// pas de bateau ici ?
		}
		return valide;
	}

}
