package com.au2001.minecraftsweeper.api.mc;

import com.au2001.minecraftsweeper.api.BombGrid;
import com.au2001.minecraftsweeper.api.GameStorage;
import com.au2001.minecraftsweeper.api.storage.TempGameStorage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class MCBombGrid extends BombGrid {

	protected final int squareSize;

	public MCBombGrid(World world) {
		this(world, 0.1);
	}

	public MCBombGrid(World world, double bombChance) {
		this(world, bombChance, 1);
	}

	public MCBombGrid(World world, int squareSize) {
		this(world, 0.1, squareSize);
	}

	public MCBombGrid(World world, double bombChance, int squareSize) {
		this(world, new TempGameStorage(), bombChance, squareSize);
	}

	public MCBombGrid(World world, GameStorage gameStorage) {
		this(world, gameStorage, 0.1);
	}

	public MCBombGrid(World world, GameStorage gameStorage, double bombChance) {
		this(world, gameStorage, bombChance, 1);
	}

	public MCBombGrid(World world, GameStorage gameStorage, int squareSize) {
		this(world, gameStorage, 0.1, squareSize);
	}

	public MCBombGrid(World world, GameStorage gameStorage, double bombChance, int squareSize) {
		super(world != null? world.getUID() : null, gameStorage, bombChance);

		if (squareSize <= 0) squareSize = 1;

		this.squareSize = squareSize;
	}

	public boolean isBomb(Location location) {
		if (location.getWorld() == null || !location.getWorld().getUID().equals(this.uuid)) return false;
		return super.isBomb(Math.floorDiv(location.getBlockX(), this.squareSize), Math.floorDiv(location.getBlockZ(), this.squareSize));
	}

	public boolean isBomb(Block block) {
		if (block.getWorld() == null || !block.getWorld().getUID().equals(this.uuid)) return false;
		return super.isBomb(Math.floorDiv(block.getX(), this.squareSize), Math.floorDiv(block.getZ(), this.squareSize));
	}

}
