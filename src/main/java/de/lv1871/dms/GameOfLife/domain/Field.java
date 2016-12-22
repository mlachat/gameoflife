package de.lv1871.dms.GameOfLife.domain;

public class Field {

	private final int x;
	private final int y;
	private final boolean alive;

	public Field(int x, int y, boolean alive) {
		this.x = x;
		this.y = y;
		this.alive = alive;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public boolean getAlive() {
		return alive;
	}

	@Override
	public String toString() {
		return "Field [x=" + x + ", y=" + y + ", alive=" + alive + "]";
	}
	
	

}
