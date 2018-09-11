package com.au2001.minecraftsweeper.plugin.outputs;

import com.au2001.minecraftsweeper.api.GameStorage;
import com.au2001.minecraftsweeper.api.Output;
import com.au2001.minecraftsweeper.api.Square;
import com.au2001.minecraftsweeper.plugin.BuildStatics;
import com.au2001.minecraftsweeper.plugin.BuildStatics.Point;
import com.au2001.minecraftsweeper.plugin.MCSConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.material.MaterialData;

import java.util.Map;
import java.util.Map.Entry;

public class SquarePainterOutput extends Output {

	protected final GameStorage gameStorage;
	protected final MCSConfig config;

	public SquarePainterOutput(GameStorage gameStorage, MCSConfig config) {
		super();

		this.gameStorage = gameStorage;
		this.config = config;
	}

	@Override
	public void sweep(Square square) {
		for (int dx = -this.config.detectRange; dx <= this.config.detectRange; dx++) {
			for (int dy = -this.config.detectRange; dy <= this.config.detectRange; dy++) {
				if (this.config.rollbackCircle && dx * dx + dy * dy > this.config.detectRange * this.config.detectRange) continue;
				this.paint(square.getRelative(dx, dy));
			}
		}
	}

	@Override
	public void rollback(Square square) {
		for (int dx = -this.config.detectRange; dx <= this.config.detectRange; dx++) {
			for (int dy = -this.config.detectRange; dy <= this.config.detectRange; dy++) {
				if (this.config.rollbackCircle && dx * dx + dy * dy > this.config.detectRange * this.config.detectRange) continue;
				this.paint(square.getRelative(dx, dy));
			}
		}
	}

	public void flag(Square square) {
		this.paint(square);
	}

	public void unflag(Square square) {
		this.paint(square);
	}

	@SuppressWarnings("deprecation")
	public void paint(Square square) {
		Map<Point, MaterialData> drawing;

		if (this.gameStorage.isSweeped(square.x, square.y)) {
			if (square.isBomb()) {
				drawing = BuildStatics.BOMB;
			} else {
				int bombs = square.getBombCount(this.config.detectRange, this.config.detectCircle);
				switch(bombs) {
				case 0:
					drawing = BuildStatics.ZERO;
					break;
				case 1:
					drawing = BuildStatics.ONE;
					break;
				case 2:
					drawing = BuildStatics.TWO;
					break;
				case 3:
					drawing = BuildStatics.THREE;
					break;
				case 4:
					drawing = BuildStatics.FOUR;
					break;
				case 5:
					drawing = BuildStatics.FIVE;
					break;
				case 6:
					drawing = BuildStatics.SIX;
					break;
				case 7:
					drawing = BuildStatics.SEVEN;
					break;
				case 8:
					drawing = BuildStatics.EIGHT;
					break;
				default:
					World world = Bukkit.getWorld(this.config.worldName);
					if (this.isChunkGenerated(world, square.x * this.config.squareSize + 4, square.y * this.config.squareSize + 4)) {
						Block center = world.getBlockAt(square.x * this.config.squareSize + 4, this.config.mapHeight, square.y * this.config.squareSize + 4);
						center.setType(Material.SIGN);
						Sign sign = (Sign) center.getState();
						sign.setLine(0, Integer.toString(bombs));
					}
					return;
				}
			}
		} else if (this.gameStorage.isFlagged(square.x, square.y)) {
			drawing = BuildStatics.FLAG;
		} else {
			drawing = BuildStatics.UNKNOWN;
		}

		if (drawing != null) {
			World world = Bukkit.getWorld(this.config.worldName);

			for (Entry<Point, MaterialData> entry : drawing.entrySet()) {
				int x = square.x * this.config.squareSize + entry.getKey().x;
				int y = this.config.mapHeight - 1 + entry.getKey().y;
				int z = square.y * this.config.squareSize + entry.getKey().z;
				this.setSafeType(world, x, y, z, entry.getValue());
			}
		}
	}

	private boolean isChunkGenerated(World world, int x, int z) {
		int cX = Math.floorDiv(x, this.config.chunkSize);
		int cZ = Math.floorDiv(z, this.config.chunkSize);
		if (world.isChunkLoaded(cX, cZ)) return true;
		if (!world.loadChunk(cX, cZ, false)) return false;
		world.unloadChunkRequest(cX, cZ);
		return true;
	}

	@SuppressWarnings("deprecation")
	private void setSafeType(World world, int x, int y, int z, MaterialData type) {
		if (!this.isChunkGenerated(world, x, z)) return;
		Block block = world.getBlockAt(x, y, z);
		block.setType(type.getItemType());
		BlockState state = block.getState();
		state.setData(type);
		state.update(true);
	}

}
