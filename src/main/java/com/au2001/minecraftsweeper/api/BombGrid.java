package com.au2001.minecraftsweeper.api;

import com.au2001.minecraftsweeper.api.storage.TempGameStorage;

import java.util.Random;
import java.util.UUID;

public class BombGrid {

	public final UUID uuid;
	public final GameStorage gameStorage;
	protected final double bombChance;
	protected final long hash;

	public BombGrid() {
		this(null, null);
	}

	public BombGrid(UUID uuid) {
		this(uuid, null);
	}

	public BombGrid(GameStorage gameStorage) {
		this(null, gameStorage);
	}

	public BombGrid(int bombChance) {
		this(null, null, bombChance);
	}

	public BombGrid(UUID uuid, int bombChance) {
		this(uuid, null, bombChance);
	}

	public BombGrid(GameStorage gameStorage, int bombChance) {
		this(null, gameStorage, bombChance);
	}

	public BombGrid(UUID uuid, GameStorage gameStorage) {
		this(uuid, gameStorage, 0.1);
	}

	public BombGrid(UUID uuid, GameStorage gameStorage, double bombChance) {
		if (uuid == null) uuid = UUID.randomUUID();
		if (gameStorage == null) gameStorage = new TempGameStorage();

		this.uuid = uuid;
		this.gameStorage = gameStorage;
		this.bombChance = bombChance;

		this.hash = uuid.getLeastSignificantBits() ^ uuid.getMostSignificantBits();
	}

	public boolean isBomb(int x, int y) {
		// 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000
		// ----------------------------------- -----------------------------------  [OR]
		//                  X                                   Y
		// ----------------------------------------------------------------------- [XOR]
		//                               Reset count
		// ----------------------------------------------------------------------- [XOR]
		//                               UUID-based hash

		long seed = (((long) x << 32) | Math.abs(y)) ^ this.gameStorage.getTotal(x, y) ^ this.hash;
		if (y < 0) seed = Long.rotateLeft(seed, 37);

		Random random = new Random(seed);

		for (int i = random.nextInt(16); i > 0; i--)
			random.nextBytes(new byte[random.nextInt(64)]);

		return random.nextDouble() < this.bombChance;
	}

	public int getBombCount(int x, int y) {
		return this.getBombCount(x, y, 1);
	}

	public int getBombCount(int x, int y, int range) {
		return this.getBombCount(x, y, range, false);
	}

	public int getBombCount(int x, int y, int range, boolean circle) {
		if (range < 0) range *= -1;

		int count = 0;

		for (int dx = -range; dx <= range; dx++) {
			for (int dy = -range; dy <= range; dy++) {
				if (circle && dx * dx + dy * dy > range * range) continue;
				if (this.isBomb(x + dx, y + dy)) count++;
			}
		}

		return count;
	}

}
