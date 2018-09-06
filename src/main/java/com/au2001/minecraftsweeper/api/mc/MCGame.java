package com.au2001.minecraftsweeper.api.mc;

import com.au2001.minecraftsweeper.api.Game;
import com.au2001.minecraftsweeper.api.Input;
import com.au2001.minecraftsweeper.api.Output;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Map.Entry;
import java.util.Set;

public class MCGame extends Game {

	public MCGame(MCBombGrid grid) {
		this(grid, null, null);
	}

	public MCGame(MCBombGrid grid, Set<Input> inputs, Set<Output> outputs) {
		super(grid, inputs, outputs);
	}

	public MCSquare getSquare(int x, int y) {
		return new MCSquare(this.getBombGrid(), x, y);
	}

	@Override
	public void reset() {
		MCBombGrid grid = this.getBombGrid();
		Set<Entry<Integer, Integer>> sweeped = grid.gameStorage.getSweeped();
		grid.gameStorage.clearSweeped();
		grid.gameStorage.setResetCount(grid.gameStorage.getResetCount() + 1);
		for (Entry<Integer, Integer> entry : sweeped) {
			int x = entry.getKey(), y = entry.getValue();
			this.rollback(new MCSquare(grid, x, y), false);
		}
	}

	public World getWorld() {
		return Bukkit.getWorld(this.grid.uuid);
	}

	public int getSquareSize() {
		if (this.grid instanceof MCBombGrid)
			return ((MCBombGrid) this.grid).squareSize;
		return 1;
	}

	public MCBombGrid getBombGrid() {
		if (this.grid instanceof MCBombGrid)
			return (MCBombGrid) this.grid;
		return null;
	}

}
