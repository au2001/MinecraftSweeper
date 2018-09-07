package com.au2001.minecraftsweeper.app;

import com.au2001.minecraftsweeper.api.BombGrid;
import com.au2001.minecraftsweeper.api.Game;
import com.au2001.minecraftsweeper.api.GameStorage;
import com.au2001.minecraftsweeper.api.storage.SQLiteGameStorage;
import com.au2001.minecraftsweeper.api.storage.TempGameStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Minesweeper {

	public static void main(String[] args) {
		GameStorage gameStorage;
		if (args.length > 0 && args[0].startsWith("sqlite:"))
			gameStorage = new SQLiteGameStorage(new File(args[0].substring(7)));
		else if (args.length > 0 && args[0].equalsIgnoreCase("sqlite"))
			gameStorage = new SQLiteGameStorage(new File(args[0].substring(7)));
		else gameStorage = new TempGameStorage();

		BombGrid grid = new BombGrid(gameStorage);
		Game game = new Game(grid);

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth(), height = gd.getDisplayMode().getHeight();

		JFrame frame = new JFrame();
		frame.setTitle("Minesweeper");
		frame.setBounds(0, 0, width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JLabel label = new JLabel("Minesweeper by au2001");
		label.setBounds(25, 0, width - 150, 30);
		frame.add(label);

		JButton button = new JButton();
		button.setText("Reset");
		button.setBounds(width - 100, 0, 100, 30);
		frame.add(button);

		BoardComponent board = new BoardComponent(game);
		board.setBounds(0, 30, width, height - 30);
		frame.add(board);

		frame.add(new JLabel()); // Background
		frame.setVisible(true);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// TODO
			}
		});

		// TODO: Handle window resizing
	}

}
