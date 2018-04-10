package modeles;

import java.lang.Thread.State;
import java.util.Random;

import modeles.Position.Direction;
import modeles.bateaux.Bateau;

public class Terrain {
	private Flotte flotte;
	private int width, height;
	public enum StatusCase{EAU, COULE, BATEAU, EAUTOUCHE, BATEAUTOUCHE};
	private StatusCase cases[][];
	Random rand;
			
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

		this.rand = new Random();
	}
	
	public boolean effectuerTir(Position position) {
		if(testerTir(position)) {
			switch (flotte.effectuerTir(position)) {
			case TOUCHE:
				cases[position.getX()][position.getY()] = StatusCase.BATEAUTOUCHE;
				break;
			case EAU:
				cases[position.getX()][position.getY()] = StatusCase.EAUTOUCHE;				
				break;
			case COULE:
				Bateau b = this.flotte.getBateau(position);
				int x = b.getPosition().getX();
				int y = b.getPosition().getY();
				int taille = b.getTaille();
				
				for(int i = 0; i < taille; i++) {
					if(b.getPosition().getDirection() == Direction.HORIZONTAL) {
						this.cases[x+i][y] = StatusCase.COULE;
					}
					else {
						this.cases[x][y+i] = StatusCase.COULE;
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
			Bateau b = flotte.bateauSuivantAPlacer();
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
