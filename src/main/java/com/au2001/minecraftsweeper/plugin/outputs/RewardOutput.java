package com.au2001.minecraftsweeper.plugin.outputs;

import com.au2001.minecraftsweeper.api.Game;
import com.au2001.minecraftsweeper.api.Input;
import com.au2001.minecraftsweeper.api.Output;
import com.au2001.minecraftsweeper.api.Square;
import com.au2001.minecraftsweeper.plugin.MCSConfig;
import com.au2001.minecraftsweeper.plugin.inputs.KeyItemInput;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RewardOutput extends Output {

	protected final Game game;
	protected final MCSConfig config;

	public RewardOutput(Game game, MCSConfig config) {
		super();

		this.game = game;
		this.config = config;
	}

	@Override
	public void sweep(Square square) {
		if (square.isBomb()) {
			this.reward(this.config.rewardSweepBomb);
		} else {
			switch(square.getBombCount()) {
			case 0:
				this.reward(this.config.rewardSweep0);
				break;
			case 1:
				this.reward(this.config.rewardSweep1);
				break;
			case 2:
				this.reward(this.config.rewardSweep2);
				break;
			case 3:
				this.reward(this.config.rewardSweep3);
				break;
			case 4:
				this.reward(this.config.rewardSweep4);
				break;
			case 5:
				this.reward(this.config.rewardSweep5);
				break;
			case 6:
				this.reward(this.config.rewardSweep6);
				break;
			case 7:
				this.reward(this.config.rewardSweep7);
				break;
			case 8:
				this.reward(this.config.rewardSweep8);
				break;
			}
		}
	}

	@Override
	public void rollback(Square square) {
		if (square.isBomb()) {
			this.reward(this.config.rewardRollbackBomb);
		} else {
			switch(square.getBombCount()) {
			case 0:
				this.reward(this.config.rewardRollback0);
				break;
			case 1:
				this.reward(this.config.rewardRollback1);
				break;
			case 2:
				this.reward(this.config.rewardRollback2);
				break;
			case 3:
				this.reward(this.config.rewardRollback3);
				break;
			case 4:
				this.reward(this.config.rewardRollback4);
				break;
			case 5:
				this.reward(this.config.rewardRollback5);
				break;
			case 6:
				this.reward(this.config.rewardRollback6);
				break;
			case 7:
				this.reward(this.config.rewardRollback7);
				break;
			case 8:
				this.reward(this.config.rewardRollback8);
				break;
			}
		}
	}

	@Override
	public void flag(Square square) {
		if (square.isBomb()) {
			this.reward(this.config.rewardFlagBomb);
		} else {
			switch(square.getBombCount()) {
			case 0:
				this.reward(this.config.rewardFlag0);
				break;
			case 1:
				this.reward(this.config.rewardFlag1);
				break;
			case 2:
				this.reward(this.config.rewardFlag2);
				break;
			case 3:
				this.reward(this.config.rewardFlag3);
				break;
			case 4:
				this.reward(this.config.rewardFlag4);
				break;
			case 5:
				this.reward(this.config.rewardFlag5);
				break;
			case 6:
				this.reward(this.config.rewardFlag6);
				break;
			case 7:
				this.reward(this.config.rewardFlag7);
				break;
			case 8:
				this.reward(this.config.rewardFlag8);
				break;
			}
		}
	}

	@Override
	public void unflag(Square square) {
		if (square.isBomb()) {
			this.reward(this.config.rewardUnflagBomb);
		} else {
			switch(square.getBombCount()) {
			case 0:
				this.reward(this.config.rewardUnflag0);
				break;
			case 1:
				this.reward(this.config.rewardUnflag1);
				break;
			case 2:
				this.reward(this.config.rewardUnflag2);
				break;
			case 3:
				this.reward(this.config.rewardUnflag3);
				break;
			case 4:
				this.reward(this.config.rewardUnflag4);
				break;
			case 5:
				this.reward(this.config.rewardUnflag5);
				break;
			case 6:
				this.reward(this.config.rewardUnflag6);
				break;
			case 7:
				this.reward(this.config.rewardUnflag7);
				break;
			case 8:
				this.reward(this.config.rewardUnflag8);
				break;
			}
		}
	}

	private void reward(List<String> rewards) {
		if (rewards == null || rewards.isEmpty()) return;

		Player sweeper = null;
		for (Input input : game.getInputs()) {
			if (input instanceof KeyItemInput) {
				sweeper = ((KeyItemInput) input).getSweeper();
				break;
			}
		}

		String name = sweeper != null? sweeper.getName() : "";

		CommandSender sender = Bukkit.getConsoleSender();
		for (String reward : rewards) {
			reward = reward.replace("{PLAYER}", name);
			Bukkit.dispatchCommand(sender, reward);
		}
	}

}
