package com.au2001.minecraftsweeper.api.mc.inputs;

import com.au2001.minecraftsweeper.api.Input;
import com.au2001.minecraftsweeper.api.mc.MCGame;
import com.au2001.minecraftsweeper.api.mc.MCSquare;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

import java.io.Closeable;

public class MCPlayerInteractInput extends Input implements Listener, Closeable {

	protected final MCGame game;

	public MCPlayerInteractInput(MCGame game, Plugin plugin) {
		super();

		this.game = game;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getClickedBlock() == null) return;
		if (!event.getHand().equals(EquipmentSlot.HAND)) return;
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		if (!event.getClickedBlock().getWorld().equals(this.game.getWorld())) return;

		event.setCancelled(true);
		this.sweep(new MCSquare(this.game.getBombGrid(), event.getClickedBlock().getLocation()));
	}

	@Override
	public void close() {
		HandlerList.unregisterAll(this);
	}

}
