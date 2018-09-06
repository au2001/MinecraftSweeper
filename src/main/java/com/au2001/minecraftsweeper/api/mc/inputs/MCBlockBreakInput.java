package com.au2001.minecraftsweeper.api.mc.inputs;

import com.au2001.minecraftsweeper.api.Input;
import com.au2001.minecraftsweeper.api.mc.MCGame;
import com.au2001.minecraftsweeper.api.mc.MCSquare;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import java.io.Closeable;

public class MCBlockBreakInput extends Input implements Listener, Closeable {

	protected final MCGame game;

	public MCBlockBreakInput(MCGame game, Plugin plugin) {
		super();

		this.game = game;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (!event.getBlock().getWorld().equals(this.game.getWorld())) return;

		event.setCancelled(true);
		this.sweep(new MCSquare(this.game.getBombGrid(), event.getBlock().getLocation()));
	}

	@Override
	public void close() {
		HandlerList.unregisterAll(this);
	}

}
