package modeles;

public class Terrain {
	private Flotte flotteJ1, flotteJ2;
	private int width, height;
	
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

	public Terrain(Flotte flJ1, Flotte flJ2) {
		this.setFlotteJ1(flJ1);
		this.setFlotteJ2(flJ2);		
		this.width = 10;
		this.height = 10;
	}
	
	public void effectuerTir(int numeroJoueur, Position position) {
		
	}

	public Flotte getFlotteJ2() {
		return flotteJ2;
	}

	public void setFlotteJ2(Flotte flotteJ2) {
		this.flotteJ2 = flotteJ2;
	}

	public Flotte getFlotteJ1() {
		return flotteJ1;
	}

	public void setFlotteJ1(Flotte flotteJ1) {
		this.flotteJ1 = flotteJ1;
	}

}
