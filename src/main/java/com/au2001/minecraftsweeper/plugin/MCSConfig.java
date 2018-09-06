package com.au2001.minecraftsweeper.plugin;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

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
	}

	public static enum StorageType {

		BINARY,
		SQLITE;

	}

}
