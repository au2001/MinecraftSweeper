package com.au2001.minecraftsweeper.api;

public abstract class Output {

	public abstract void sweep(Square square);
	public abstract void rollback(Square square);

	public abstract void flag(Square square);
	public abstract void unflag(Square square);

}
