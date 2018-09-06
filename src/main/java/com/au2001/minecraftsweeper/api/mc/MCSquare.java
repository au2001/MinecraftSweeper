package com.au2001.minecraftsweeper.api.mc;

import com.au2001.minecraftsweeper.api.Square;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class MCSquare extends Square {

	public MCSquare(Block block) {
		this(block.getWorld(), block.getX(), block.getZ());
	}

	public MCSquare(Location location) {
		this(location.getWorld(), location.getBlockX(), location.getBlockZ());
	}

	public MCSquare(Block block, int squareSize) {
		this(block.getWorld(), Math.floorDiv(block.getX(), squareSize), Math.floorDiv(block.getZ(), squareSize), squareSize);
	}

	public MCSquare(Location location, int squareSize) {
		this(location.getWorld(), Math.floorDiv(location.getBlockX(), squareSize), Math.floorDiv(location.getBlockZ(), squareSize), squareSize);
	}

	public MCSquare(MCBombGrid grid, Location location) {
		this(grid, Math.floorDiv(location.getBlockX(), grid.squareSize), Math.floorDiv(location.getBlockZ(), grid.squareSize));
	}

	public MCSquare(World world, int x, int y) {
		this(world, x, y, 1);
	}

	public MCSquare(World world, int x, int y, int squareSize) {
		this(new MCBombGrid(world, squareSize), x, y);
	}

	public MCSquare(MCBombGrid grid, int x, int y) {
		super(grid, x, y);
	}

	public MCSquare getRelative(int x, int y) {
		if (x == 0 && y == 0) return this;
		MCBombGrid grid = this.grid instanceof MCBombGrid? (MCBombGrid) this.grid : null;
		return new MCSquare(grid, this.x + x, this.y + y);
	}

}
