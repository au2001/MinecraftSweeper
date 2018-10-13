package com.au2001.minecraftsweeper.plugin;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MCSConfig {

	public final int chunkSize;
	public final String worldName;
	public final int mapHeight;
	public final double bombChance;
	public final int squareSize;
	public final int maxChained;
	public final int detectRange;
	public final boolean detectCircle;
	public final ItemStack keyItem;
	public final boolean consumeKey;
	public final int rollbackRange;
	public final boolean rollbackCircle;
	public final long rollbackDelay;
	public final String resetMessage;
	public final String fileName;
	public final StorageType storageType;
	public final boolean debug;
	public final List<String> rewardSweep0, rewardSweep1, rewardSweep2, rewardSweep3, rewardSweep4, rewardSweep5, rewardSweep6, rewardSweep7, rewardSweep8, rewardSweepBomb;
	public final List<String> rewardRollback0, rewardRollback1, rewardRollback2, rewardRollback3, rewardRollback4, rewardRollback5, rewardRollback6, rewardRollback7, rewardRollback8, rewardRollbackBomb;
	public final List<String> rewardFlag0, rewardFlag1, rewardFlag2, rewardFlag3, rewardFlag4, rewardFlag5, rewardFlag6, rewardFlag7, rewardFlag8, rewardFlagBomb;
	public final List<String> rewardUnflag0, rewardUnflag1, rewardUnflag2, rewardUnflag3, rewardUnflag4, rewardUnflag5, rewardUnflag6, rewardUnflag7, rewardUnflag8, rewardUnflagBomb;

	public MCSConfig(ConfigurationSection config) {
		this.chunkSize = config.getInt("chunk_size", 16);
		this.worldName = config.getString("world_name", "world");
		this.mapHeight = config.getInt("map_height", 1);
		this.bombChance = config.getDouble("bomb_chance", 0.1);
		this.squareSize = config.getInt("square_size", 1);
		this.maxChained = config.getInt("max_chained", 0);
		this.detectRange = config.getInt("detect_range", 1);
		this.detectCircle = config.getBoolean("detect_circle", false);
		this.keyItem = config.getItemStack("key_item", null);
		this.consumeKey = config.getBoolean("consume_key", false);
		this.rollbackRange = config.getInt("rollback_range", -1);
		this.rollbackCircle = config.getBoolean("rollback_circle", false);
		this.rollbackDelay = config.getLong("rollback_delay", 0);
		this.resetMessage = config.getString("reset_message", "Reset!");
		this.fileName = config.getString("file_name", "storage.bin");
		StorageType storageType = null;
		try {
			storageType = StorageType.valueOf(config.getString("storage_type", "BINARY").toUpperCase());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			this.storageType = storageType;
		}
		this.debug = config.getBoolean("debug", false);
		this.rewardSweep0 = config.getStringList("reward.sweep.0");
		this.rewardSweep1 = config.getStringList("reward.sweep.1");
		this.rewardSweep2 = config.getStringList("reward.sweep.2");
		this.rewardSweep3 = config.getStringList("reward.sweep.3");
		this.rewardSweep4 = config.getStringList("reward.sweep.4");
		this.rewardSweep5 = config.getStringList("reward.sweep.5");
		this.rewardSweep6 = config.getStringList("reward.sweep.6");
		this.rewardSweep7 = config.getStringList("reward.sweep.7");
		this.rewardSweep8 = config.getStringList("reward.sweep.8");
		this.rewardSweepBomb = config.getStringList("reward.sweep.bomb");
		this.rewardRollback0 = config.getStringList("reward.rollback.0");
		this.rewardRollback1 = config.getStringList("reward.rollback.1");
		this.rewardRollback2 = config.getStringList("reward.rollback.2");
		this.rewardRollback3 = config.getStringList("reward.rollback.3");
		this.rewardRollback4 = config.getStringList("reward.rollback.4");
		this.rewardRollback5 = config.getStringList("reward.rollback.5");
		this.rewardRollback6 = config.getStringList("reward.rollback.6");
		this.rewardRollback7 = config.getStringList("reward.rollback.7");
		this.rewardRollback8 = config.getStringList("reward.rollback.8");
		this.rewardRollbackBomb = config.getStringList("reward.rollback.bomb");
		this.rewardFlag0 = config.getStringList("reward.flag.0");
		this.rewardFlag1 = config.getStringList("reward.flag.1");
		this.rewardFlag2 = config.getStringList("reward.flag.2");
		this.rewardFlag3 = config.getStringList("reward.flag.3");
		this.rewardFlag4 = config.getStringList("reward.flag.4");
		this.rewardFlag5 = config.getStringList("reward.flag.5");
		this.rewardFlag6 = config.getStringList("reward.flag.6");
		this.rewardFlag7 = config.getStringList("reward.flag.7");
		this.rewardFlag8 = config.getStringList("reward.flag.8");
		this.rewardFlagBomb = config.getStringList("reward.flag.bomb");
		this.rewardUnflag0 = config.getStringList("reward.unflag.0");
		this.rewardUnflag1 = config.getStringList("reward.unflag.1");
		this.rewardUnflag2 = config.getStringList("reward.unflag.2");
		this.rewardUnflag3 = config.getStringList("reward.unflag.3");
		this.rewardUnflag4 = config.getStringList("reward.unflag.4");
		this.rewardUnflag5 = config.getStringList("reward.unflag.5");
		this.rewardUnflag6 = config.getStringList("reward.unflag.6");
		this.rewardUnflag7 = config.getStringList("reward.unflag.7");
		this.rewardUnflag8 = config.getStringList("reward.unflag.8");
		this.rewardUnflagBomb = config.getStringList("reward.unflag.bomb");
	}

	public static enum StorageType {

		BINARY,
		SQLITE;

	}

}
