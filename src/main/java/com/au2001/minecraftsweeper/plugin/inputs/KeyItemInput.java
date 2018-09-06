package com.au2001.minecraftsweeper.plugin.inputs;

import com.au2001.minecraftsweeper.api.Input;
import com.au2001.minecraftsweeper.api.Square;
import com.au2001.minecraftsweeper.api.mc.MCGame;
import com.au2001.minecraftsweeper.api.mc.MCSquare;
import com.au2001.minecraftsweeper.plugin.MCSConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.Closeable;

public class KeyItemInput extends Input implements Listener, Closeable {

	protected final MCGame game;
	protected final MCSConfig config;

	public KeyItemInput(MCGame game, MCSConfig config, Plugin plugin) {
		super();

		this.game = game;
		this.config = config;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getClickedBlock() == null) return;
		if (!event.getHand().equals(EquipmentSlot.HAND)) return;
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;
		if (!event.getClickedBlock().getWorld().equals(this.game.getWorld())) return;

		event.setCancelled(true);
		event.setUseInteractedBlock(Result.DENY);

		Square square = new MCSquare(this.game.getBombGrid(), event.getClickedBlock().getLocation());

		if (this.game.grid.gameStorage.isSweeped(square.x, square.y)) {
			event.setUseItemInHand(Result.DENY);
			return;
		}

		if (this.config.keyItem != null) {
			if (event.getItem() == null || !event.getItem().isSimilar(this.config.keyItem) || event.getItem().getAmount() < this.config.keyItem.getAmount()) {
				event.setUseItemInHand(Result.DENY);
				return;
			}

			event.setUseItemInHand(Result.ALLOW);

			if (this.config.consumeKey) {
				ItemStack item = event.getItem();
				if (item.getAmount() > this.config.keyItem.getAmount()) {
					item.setAmount(item.getAmount() - this.config.keyItem.getAmount());
				} else item = null;
				event.getPlayer().getInventory().setItemInMainHand(item);
			}
		}

		this.sweep(square);
	}

	@Override
	public void close() {
		HandlerList.unregisterAll(this);
	}

}
