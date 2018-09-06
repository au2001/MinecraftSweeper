package com.au2001.minecraftsweeper.plugin.outputs;

import com.au2001.minecraftsweeper.api.Game;
import com.au2001.minecraftsweeper.api.Output;
import com.au2001.minecraftsweeper.api.Square;
import com.au2001.minecraftsweeper.plugin.MCSConfig;
import com.au2001.minecraftsweeper.plugin.MinecraftSweeper;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.CancellationException;

public class BombRollbackOutput extends Output {

	protected final Game game;
	protected final MCSConfig config;

	public BombRollbackOutput(Game game, MCSConfig config) {
		super();

		this.game = game;
		this.config = config;
	}

	public void sweep(Square square) {
		if (square.isBomb()) {
			int range = this.config.rollbackRange;

			if (this.config.rollbackDelay > 0) {
				new BukkitRunnable() {
					public void run() {
						try {
							Thread.sleep(BombRollbackOutput.this.config.rollbackDelay);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						for (int dx = -range; dx <= range; dx++) {
							for (int dy = -range; dy <= range; dy++) {
								if (BombRollbackOutput.this.config.rollbackCircle && dx * dx + dy * dy > range * range) continue;
								if (!BombRollbackOutput.this.game.grid.gameStorage.isSweeped(square.x + dx, square.y + dy)) continue;
								BombRollbackOutput.this.game.rollback(square.getRelative(dx, dy));
							}
						}

						// TODO: Pyrotechnic effects
					}
				}.runTaskLater(MinecraftSweeper.getInstance(), this.config.rollbackDelay);
			} else {
				for (int dx = -range; dx <= range; dx++) {
					for (int dy = -range; dy <= range; dy++) {
						if (this.config.rollbackCircle && dx * dx + dy * dy > range * range) continue;
						this.game.rollback(square.getRelative(dx, dy)); // TODO: Doesn't regenerate bombs
					}
				}

				// TODO: Pyrotechnic effects

				throw new CancellationException("Hit a bomb resetting the area");
			}
		}
	}

	public void rollback(Square square) { /* No Op */ }

	public void flag(Square square) { /* No Op */ }
	public void unflag(Square square) { /* No Op */ }

}
