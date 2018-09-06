package com.au2001.minecraftsweeper.api;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Game extends Input implements Closeable {

	protected final Set<Input> inputs;
	public final BombGrid grid;

	protected final Output proxyOutput = new Output() {
		@Override
		public void sweep(Square square) {
			Game.this.sweep(square);
		}

		@Override
		public void rollback(Square square) {
			Game.this.rollback(square);
		}

		@Override
		public void flag(Square square) {
			Game.this.flag(square);
		}

		@Override
		public void unflag(Square square) {
			Game.this.unflag(square);
		}
	};

	public Game(BombGrid grid) {
		this(grid, null, null);
	}

	public Game(BombGrid grid, Set<Input> inputs, Set<Output> outputs) {
		super(outputs);

		this.grid = grid;
		this.inputs = inputs != null? new LinkedHashSet<Input>(inputs) : new LinkedHashSet<Input>();
	}

	@Override
	public void sweep(Square square) {
		if (this.grid.gameStorage.isSweeped(square.x, square.y)) return;
		this.grid.gameStorage.setSweeped(square.x, square.y, true);
		super.sweep(square);
	}

	@Override
	public void rollback(Square square) {
		this.rollback(square, true);
	}

	protected void rollback(Square square, boolean increment) {
		if (increment) {
			if (!this.grid.gameStorage.isSweeped(square.x, square.y)) return;
			this.grid.gameStorage.setSweeped(square.x, square.y, false);
			long rollbackCount = this.grid.gameStorage.getRollbackCount(square.x, square.y);
			this.grid.gameStorage.setRollbackCount(square.x, square.y, ++rollbackCount);
		}
		super.rollback(square);
	}

	@Override
	public void flag(Square square) {
		// TODO
		super.flag(square);
	}

	@Override
	public void unflag(Square square) {
		// TODO
		super.unflag(square);
	}

	public void reset() {
		Set<Entry<Integer, Integer>> sweeped = this.grid.gameStorage.getSweeped();
		this.grid.gameStorage.clearSweeped();
		this.grid.gameStorage.setResetCount(this.grid.gameStorage.getResetCount() + 1);
		for (Entry<Integer, Integer> entry : sweeped) {
			int x = entry.getKey(), y = entry.getValue();
			this.rollback(new Square(this.grid, x, y), false);
		}
	}

	public Square getSquare(int x, int y) {
		return new Square(this.grid, x, y);
	}

	@Override
	public void removeOutput(Output output) {
		super.removeOutput(output);
		if (output instanceof Closeable) {
			try {
				((Closeable) output).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Set<Input> getInputs() {
		return Collections.unmodifiableSet(inputs);
	}

	public void addInput(Input input) {
		input.addOutput(this.proxyOutput);
		this.inputs.add(input);
	}

	public void removeInput(Input input) {
		input.removeOutput(this.proxyOutput);
		this.inputs.remove(input);
		if (input instanceof Closeable) {
			try {
				((Closeable) input).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void clearInputs() {
		for (Input input : new LinkedHashSet<Input>(this.inputs))
			this.removeInput(input);
	}

	@Override
	public void close() {
		this.clearInputs();
		this.clearOutputs();
	}

}
