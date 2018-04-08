package modeles;

public class Position {
	private int X;
	private int Y;
	public Direction direction;
	public enum Direction { HORIZONTAL, VERTICAL};

	public Position() {
		this.setX(0);
		this.setY(0);
		this.setDirection(Direction.HORIZONTAL);
	}

	public Position(int x, int y, Direction dir) {
		this.setX(x);
		this.setY(y);
		this.direction = dir;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction d) {
		this.direction = d;
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
