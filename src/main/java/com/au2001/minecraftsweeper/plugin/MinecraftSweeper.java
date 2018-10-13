package com.au2001.minecraftsweeper.plugin;

import com.au2001.minecraftsweeper.api.BombGrid;
import com.au2001.minecraftsweeper.api.GameStorage;
import com.au2001.minecraftsweeper.api.Square;
import com.au2001.minecraftsweeper.api.mc.MCBombGrid;
import com.au2001.minecraftsweeper.api.mc.MCGame;
import com.au2001.minecraftsweeper.api.mc.MCSquare;
import com.au2001.minecraftsweeper.api.outputs.ChainReactionOutput;
import com.au2001.minecraftsweeper.api.outputs.DebugOutput;
import com.au2001.minecraftsweeper.api.storage.BinaryGameStorage;
import com.au2001.minecraftsweeper.api.storage.SQLiteGameStorage;
import com.au2001.minecraftsweeper.api.storage.TempGameStorage;
import com.au2001.minecraftsweeper.plugin.MCSConfig.StorageType;
import com.au2001.minecraftsweeper.plugin.inputs.KeyItemInput;
import com.au2001.minecraftsweeper.plugin.outputs.BombRollbackOutput;
import com.au2001.minecraftsweeper.plugin.outputs.RewardOutput;
import com.au2001.minecraftsweeper.plugin.outputs.SquarePainterOutput;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MinecraftSweeper extends JavaPlugin {

	private static MinecraftSweeper instance = null;

	protected MCSConfig config;
	protected GameStorage gameStorage;
	protected MCBombGrid bombGrid;
	protected MCGame game;

	@Override
	public void onLoad() {
		if (MinecraftSweeper.instance == null) {
			MinecraftSweeper.instance = this;

			saveDefaultConfig();
			reloadConfig();

			this.config = new MCSConfig(getConfig());
			if (this.config.storageType == StorageType.BINARY)
				this.gameStorage = new BinaryGameStorage(new File(this.getDataFolder(), this.config.fileName));
			else if (this.config.storageType == StorageType.SQLITE)
				this.gameStorage = new SQLiteGameStorage(new File(this.getDataFolder(), this.config.fileName));
			else this.gameStorage = new TempGameStorage();
		}
	}

	@Override
	public void onEnable() {
		if (MinecraftSweeper.instance == null) {
			MinecraftSweeper.instance = this;

			saveDefaultConfig();
			reloadConfig();

			this.config = new MCSConfig(getConfig());
			if (this.config.storageType == StorageType.BINARY)
				this.gameStorage = new BinaryGameStorage(new File(this.getDataFolder(), this.config.fileName));
			else if (this.config.storageType == StorageType.SQLITE)
				this.gameStorage = new SQLiteGameStorage(new File(this.getDataFolder(), this.config.fileName));
			else this.gameStorage = new TempGameStorage();
		}

//		if (this.config.debug) {
//			ItemStack keyItem = new ItemStack(Material.TRIPWIRE_HOOK);
//			ItemMeta keyMeta = keyItem.getItemMeta();
//			keyMeta.setDisplayName(ChatColor.BLUE + "Minecraft Sweeper Key");
//			keyMeta.setLore(Arrays.asList(ChatColor.GRAY + "Click on a square to reveal its", ChatColor.GRAY + "contents, but beware of bombs!"));
//			keyMeta.setUnbreakable(true);
//			keyMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
//			keyItem.setItemMeta(keyMeta);
//			getConfig().set("key_item", keyItem);
//			saveConfig();
//		}

		World world = Bukkit.getWorld(MinecraftSweeper.this.config.worldName);
		if (world == null) {
			new BukkitRunnable() {
				public void run() {
					World world = Bukkit.getWorld(MinecraftSweeper.this.config.worldName);
					if (world == null) {
						WorldCreator creator = new WorldCreator(MinecraftSweeper.this.config.worldName);
						creator.type(WorldType.CUSTOMIZED);
						creator.environment(Environment.NORMAL);
						creator.generateStructures(false);
						creator.generator(MinecraftSweeper.this.getDefaultWorldGenerator(creator.name(), ""));
						world = creator.createWorld();
					}
					setup(world);
				}
			}.runTask(this);
		} else setup(world);
	}

	@Override
	public void onDisable() {
		if (this.config.debug) this.game.reset();
		if (this.game != null) this.game.close();

		this.config = null;
		this.gameStorage = null;
		this.bombGrid = null;
		this.game = null;

		MinecraftSweeper.instance = null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		this.game.reset();
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.config.resetMessage));
		return true;
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String world, String id) {
		if (world != null && world.equals(this.config.worldName))
			return new MCSChunkGenerator(this.gameStorage, this.config);

		return super.getDefaultWorldGenerator(world, id);
	}

	private void setup(World world) {
		this.bombGrid = new MCBombGrid(world, this.gameStorage, this.config.bombChance, this.config.squareSize);
		this.game = new MCGame(this.bombGrid);

		// this.game.addInput(new MCBlockBreakInput(this.game, this));
		// this.game.addInput(new MCPlayerInteractInput(this.game, this));
		this.game.addInput(new KeyItemInput(this.game, this.config, this));

		if (this.config.rollbackRange >= 0) this.game.addOutput(new BombRollbackOutput(this.game, this.config));
		this.game.addOutput(new SquarePainterOutput(this.gameStorage, this.config));
		this.game.addOutput(new RewardOutput(this.game, this.config));

		if (this.config.debug) {
			this.game.reset();
			this.game.addOutput(new DebugOutput());

			for (int x = -25; x <= 25; x++) {
				for (int y = -25; y <= 25; y++) {
					Square square = new MCSquare(this.bombGrid, x, y);
					if (!square.isBomb()) this.game.sweep(square);
				}
			}
		}

		// Add chain reaction after debug to prevent sweeping too much
		if (this.config.maxChained > 0)
			this.game.addOutput(new ChainReactionOutput(this.game, this.config.detectRange, this.config.detectCircle, this.config.maxChained));
	}

	public static MinecraftSweeper getInstance() {
		return MinecraftSweeper.instance;
	}

	private static class MCSChunkGenerator extends ChunkGenerator {

		protected final GameStorage gameStorage;
		protected final MCSConfig config;
		protected final SquarePainterOutput squarePainter;

		private MCSChunkGenerator(GameStorage gameStorage, MCSConfig config) {
			super();

			this.gameStorage = gameStorage;
			this.config = config;

			this.squarePainter = new SquarePainterOutput(gameStorage, config);
		}

		@Override
		public Location getFixedSpawnLocation(World world, Random random) {
			return new Location(world, 0, this.config.mapHeight, 0, 180, 0);
		}

		@Override
		@SuppressWarnings("deprecation")
		public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biomeGrid) {
			Biome biome;
			try {
				biome = Biome.valueOf("THE_VOID");
			} catch (IllegalArgumentException e) {
				biome = Biome.valueOf("VOID");
			}
			for (int cX = 0; cX < this.config.chunkSize; cX++)
				for (int cZ = 0; cZ < this.config.chunkSize; cZ++)
					biomeGrid.setBiome(cX, cZ, biome);

			ChunkData data = createChunkData(world);

			data.setRegion(0, 0, 0, this.config.chunkSize, 1, this.config.chunkSize, Material.BEDROCK); // Bedrock layer
			try {
				data.setRegion(0, 1, 0, this.config.chunkSize, this.config.mapHeight + 1, this.config.chunkSize, Material.WHITE_CONCRETE); // Fill
			} catch (NoSuchFieldError e) {
				MaterialData materialData = new MaterialData(Material.valueOf("CONCRETE"), (byte) 0); // White concrete
				data.setRegion(0, 1, 0, this.config.chunkSize, this.config.mapHeight + 1, this.config.chunkSize, materialData); // Fill
			}

			int startX = -(x * this.config.chunkSize) % this.config.squareSize;
			if (startX > 0) startX -= this.config.squareSize;
			int startY = -(z * this.config.chunkSize) % this.config.squareSize;
			if (startY > 0) startY -= this.config.squareSize;

			Set<Square> pending = new HashSet<Square>();
			BombGrid bombGrid = this.gameStorage != null? new BombGrid(world.getUID(), this.gameStorage, this.config.bombChance) : null;

			for (int squareX = startX; squareX < this.config.chunkSize; squareX += this.config.squareSize) {
				for (int squareY = startY; squareY < this.config.chunkSize; squareY += this.config.squareSize) {
					for (int sX = 0; sX < this.config.squareSize; sX++) {
						if (squareX + sX < 0 || squareX + sX >= this.config.chunkSize) continue;
						for (int sY = 0; sY < this.config.squareSize; sY++) {
							if (squareY + sY < 0 || squareY + sY >= this.config.chunkSize) continue;
							if (sX != 0 && sX != this.config.squareSize - 1 && sY != 0 && sY != this.config.squareSize - 1) continue;
							try {
								data.setBlock(squareX + sX, this.config.mapHeight - 1, squareY + sY, Material.GRAY_CONCRETE); // Border
							} catch (NoSuchFieldError e) {
								MaterialData materialData = new MaterialData(Material.valueOf("CONCRETE"), (byte) 7); // Gray concrete
								data.setBlock(squareX + sX, this.config.mapHeight - 1, squareY + sY, materialData); // Border
							}
							data.setBlock(squareX + sX, this.config.mapHeight, squareY + sY, Material.AIR); // Air above border
						}
					}

					int cX = Math.floorDiv(x * this.config.chunkSize + squareX, this.config.squareSize);
					int cY = Math.floorDiv(z * this.config.chunkSize + squareY, this.config.squareSize);
					// if (this.gameStorage != null && this.gameStorage.isSweeped(cX, cY))
					pending.add(new Square(bombGrid, cX, cY));
				}
			}

			if (!pending.isEmpty()) {
				new BukkitRunnable() {
					public void run() {
						for (Square square : pending)
							MCSChunkGenerator.this.squarePainter.paint(square);
					}
				}.runTask(MinecraftSweeper.getInstance());
			}

			return data;
		}
	}

}
