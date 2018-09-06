package com.au2001.minecraftsweeper.api.outputs;

import com.au2001.minecraftsweeper.api.Output;
import com.au2001.minecraftsweeper.api.Square;

public class DebugOutput extends Output {

	@Override
	public void sweep(Square square) {
		String status = !square.isBomb()? square.getBombCount() + " bombs near" : "BOMB!";
		System.out.println("Sweeped square (" + square.x + "; " + square.y + "): " + status);
	}

	@Override
	public void rollback(Square square) {
		System.out.println("Rolled back square (" + square.x + "; " + square.y + ")");
	}

	@Override
	public void flag(Square square) {
		System.out.println("Flagged square (" + square.x + "; " + square.y + ")");
	}

	@Override
	public void unflag(Square square) {
		System.out.println("Unflagged square (" + square.x + "; " + square.y + ")");
	}

}
