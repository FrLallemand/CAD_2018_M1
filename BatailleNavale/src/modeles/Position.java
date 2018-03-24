package modeles;

public class Position {
	private int X;
	private int Y;
	private boolean direction;
	
	public Position() {
		this.setX(0);
		this.setY(0);
		this.setDirection(false);
	}

	public Position(int x, int y, Boolean dir) {
		this.setX(x);
		this.setY(y);
		this.setDirection(dir);
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
	
	

}
