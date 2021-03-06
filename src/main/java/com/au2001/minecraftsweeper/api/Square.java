package com.au2001.minecraftsweeper.api;

public class Square {

	protected final BombGrid grid;
	public final int x, y;

	public Square(BombGrid grid, int x, int y) {
		this.grid = grid;
		this.x = x;
		this.y = y;
	}

	public boolean isBomb() {
		return this.grid.isBomb(this.x, this.y);
	}

	public int getBombCount() {
		return this.getBombCount(1);
	}

	public int getBombCount(int range) {
		return this.getBombCount(range, false);
	}

	public int getBombCount(int range, boolean circle) {
		return this.grid.getBombCount(this.x, this.y, range, circle);
	}

	public Square getRelative(int x, int y) {
		if (x == 0 && y == 0) return this;
		return new Square(this.grid, this.x + x, this.y + y);
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Square)) return false;
		if (((Square) obj).x != this.x || ((Square) obj).y != this.y) return false;
		if (((Square) obj).grid == this.grid) return true;
		if (this.grid == null) return false;
		if (this.grid.equals(((Square) obj).grid)) return true;
		return false;
	}

}
