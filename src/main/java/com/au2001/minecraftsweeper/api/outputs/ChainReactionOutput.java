package com.au2001.minecraftsweeper.api.outputs;

import com.au2001.minecraftsweeper.api.Game;
import com.au2001.minecraftsweeper.api.Output;
import com.au2001.minecraftsweeper.api.Square;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CancellationException;

public class ChainReactionOutput extends Output {

	protected final Game game;
	protected final int range;
	protected final boolean circle;
	protected final int maxChained;
	protected int chaining;

	public ChainReactionOutput(Game game) {
		this(game, 1);
	}

	public ChainReactionOutput(Game game, int range) {
		this(game, range, false);
	}

	public ChainReactionOutput(Game game, int range, boolean circle) {
		this(game, range, circle, 100);
	}

	public ChainReactionOutput(Game game, int range, boolean circle, int maxChained) {
		super();

		this.game = game;
		this.range = range;
		this.circle = circle;
		this.maxChained = maxChained;
	}

	@Override
	public void sweep(Square square) {
		if (square.getBombCount(this.range, this.circle) > 0) return;
		boolean original = this.chaining == 0;
		Set<Square> chained = new HashSet<Square>();
		for (int dx = -range; dx <= range; dx++) {
			for (int dy = -range; dy <= range; dy++) {
				if (dx == 0 && dy == 0) continue;
				if (circle && dx * dx + dy * dy > range * range) continue;
				if (this.game.grid.gameStorage.isSweeped(square.x + dx, square.y + dy)) continue;
				chained.add(square.getRelative(dx, dy));
				if (this.chaining + chained.size() >= maxChained) break;
			}
			if (this.chaining + chained.size() >= maxChained) break;
		}
		if (!chained.isEmpty()) {
			this.chaining += chained.size();
			for (Square other : chained)
				this.game.grid.gameStorage.setSweeped(other.x, other.y, true);
			for (Square other : chained) {
				try {
					for (Output output : this.game.getOutputs())
						if (output != null) output.sweep(other);
				} catch (CancellationException e) {}
			}
			if (original) this.chaining = 0;
		}
	}

	@Override
	public void rollback(Square square) { /* No Op */ }

	@Override
	public void flag(Square square) { /* No Op */ }

	@Override
	public void unflag(Square square) { /* No Op */ }

}
