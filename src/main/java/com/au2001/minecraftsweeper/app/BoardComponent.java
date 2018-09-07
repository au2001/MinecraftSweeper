package com.au2001.minecraftsweeper.app;

import com.au2001.minecraftsweeper.api.Game;
import com.au2001.minecraftsweeper.api.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Random;

public class BoardComponent extends JComponent {

	public static final int SQUARE_SIZE = 16;

	protected final Game game;

	public double scrollX = 0, scrollY = 0, zoom = 1;

	public BoardComponent(Game game) {
		this.game = game;

		this.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent event) {
				int modifiers = event.getModifiers();

				if ((modifiers & InputEvent.CTRL_MASK) != 0) {
					double zoom = event.getPreciseWheelRotation();
					BoardComponent.this.zoom += zoom;
					BoardComponent.this.zoom = Math.min(BoardComponent.this.zoom, 10);
					BoardComponent.this.zoom = Math.max(BoardComponent.this.zoom, (double) 1 / BoardComponent.SQUARE_SIZE);
					BoardComponent.this.repaint();
					return;
				}

				if ((modifiers & InputEvent.SHIFT_MASK) == 0)
					BoardComponent.this.scrollY += event.getPreciseWheelRotation() * event.getScrollAmount();
				else BoardComponent.this.scrollX += event.getPreciseWheelRotation() * event.getScrollAmount();

				BoardComponent.this.repaint();
			}
		});
	}

	@Override
	public void paint(Graphics graphics) {
		int squareSize = (int) Math.floor(BoardComponent.SQUARE_SIZE * this.zoom);

		int minX = (int) Math.floor(this.scrollX / squareSize);
		int minY = (int) Math.floor(this.scrollY / squareSize);
		int maxX = (int) Math.floor((this.getWidth() + this.scrollX) / squareSize);
		int maxY = (int) Math.floor((this.getHeight() + this.scrollY) / squareSize);

		Random random = new Random();

		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				Square square = this.game.getSquare(x, y);

				int rX = Math.floorMod((int) -this.scrollX, squareSize) + squareSize * (x - minX - 1);
				int rY = Math.floorMod((int) -this.scrollY, squareSize) + squareSize * (y - minY - 1);
				graphics.setColor(Color.BLACK);
				if (true) {//this.game.grid.gameStorage.isSweeped(x, y)) {
					if (!square.isBomb()) {
						switch(square.getBombCount()) {
						case 0:
							this.paintZero(graphics, rX, rY, squareSize, squareSize);
							break;
						case 1:
							this.paintOne(graphics, rX, rY, squareSize, squareSize);
							break;
						case 2:
							this.paintTwo(graphics, rX, rY, squareSize, squareSize);
							break;
						case 3:
							this.paintThree(graphics, rX, rY, squareSize, squareSize);
							break;
						case 4:
							this.paintFour(graphics, rX, rY, squareSize, squareSize);
							break;
						case 5:
							this.paintFive(graphics, rX, rY, squareSize, squareSize);
							break;
						case 6:
							this.paintSix(graphics, rX, rY, squareSize, squareSize);
							break;
						case 7:
							this.paintSeven(graphics, rX, rY, squareSize, squareSize);
							break;
						default:
							this.paintEight(graphics, rX, rY, squareSize, squareSize);
						}
					} else this.paintBomb(graphics, rX, rY, squareSize, squareSize);
				} else if (this.game.grid.gameStorage.isFlagged(x, y))
					this.paintFlag(graphics, rX, rY, squareSize, squareSize);
				else this.paintUnknown(graphics, rX, rY, squareSize, squareSize);
			}
		}
	}

	protected void paintUnknown(Graphics graphics, int x, int y, int width, int height) {
		// TODO
	}

	protected void paintFlag(Graphics graphics, int x, int y, int width, int height) {
		// TODO
	}

	protected void paintZero(Graphics graphics, int x, int y, int width, int height) {
		// TODO
	}

	protected void paintOne(Graphics graphics, int x, int y, int width, int height) {
		graphics.setColor(Color.decode("#5555FF"));
		graphics.drawRect(x + 4, y + 6, 1, 1);

		graphics.drawRect(x + 5, y + 5, 1, 1);
		graphics.drawRect(x + 4, y + 5, 1, 1);

		graphics.drawRect(x + 4, y + 4, 1, 1);

		graphics.drawRect(x + 4, y + 3, 1, 1);

		graphics.drawRect(x + 5, y + 2, 1, 1);
		graphics.drawRect(x + 4, y + 2, 1, 1);
		graphics.drawRect(x + 3, y + 2, 1, 1);
	}

	protected void paintTwo(Graphics graphics, int x, int y, int width, int height) {
		graphics.setColor(Color.GREEN);
		graphics.drawRect(x + 5, y + 6, 1, 1);
		graphics.drawRect(x + 4, y + 6, 1, 1);
		graphics.drawRect(x + 3, y + 6, 1, 1);

		graphics.drawRect(x + 6, y + 5, 1, 1);
		graphics.drawRect(x + 2, y + 5, 1, 1);

		graphics.drawRect(x + 4, y + 4, 1, 1);
		graphics.drawRect(x + 3, y + 4, 1, 1);

		graphics.drawRect(x + 5, y + 3, 1, 1);

		graphics.drawRect(x + 6, y + 2, 1, 1);
		graphics.drawRect(x + 5, y + 2, 1, 1);
		graphics.drawRect(x + 4, y + 2, 1, 1);
		graphics.drawRect(x + 3, y + 2, 1, 1);
		graphics.drawRect(x + 2, y + 2, 1, 1);
	}

	protected void paintThree(Graphics graphics, int x, int y, int width, int height) {
		graphics.setColor(Color.ORANGE);
		graphics.drawRect(x + 5, y + 6, 1, 1);
		graphics.drawRect(x + 4, y + 6, 1, 1);
		graphics.drawRect(x + 3, y + 6, 1, 1);

		graphics.drawRect(x + 2, y + 5, 1, 1);

		graphics.drawRect(x + 4, y + 4, 1, 1);
		graphics.drawRect(x + 3, y + 4, 1, 1);

		graphics.drawRect(x + 2, y + 3, 1, 1);

		graphics.drawRect(x + 5, y + 2, 1, 1);
		graphics.drawRect(x + 4, y + 2, 1, 1);
		graphics.drawRect(x + 3, y + 2, 1, 1);
	}

	protected void paintFour(Graphics graphics, int x, int y, int width, int height) {
		graphics.setColor(Color.BLUE);
		graphics.drawRect(x + 5, y + 6, 1, 1);
		graphics.drawRect(x + 3, y + 6, 1, 1);

		graphics.drawRect(x + 6, y + 5, 1, 1);
		graphics.drawRect(x + 3, y + 5, 1, 1);

		graphics.drawRect(x + 6, y + 4, 1, 1);
		graphics.drawRect(x + 5, y + 4, 1, 1);
		graphics.drawRect(x + 4, y + 4, 1, 1);
		graphics.drawRect(x + 3, y + 4, 1, 1);
		graphics.drawRect(x + 2, y + 4, 1, 1);

		graphics.drawRect(x + 3, y + 3, 1, 1);

		graphics.drawRect(x + 3, y + 2, 1, 1);
	}

	protected void paintFive(Graphics graphics, int x, int y, int width, int height) {
		graphics.setColor(Color.RED);
		graphics.drawRect(x + 6, y + 6, 1, 1);
		graphics.drawRect(x + 5, y + 6, 1, 1);
		graphics.drawRect(x + 4, y + 6, 1, 1);
		graphics.drawRect(x + 3, y + 6, 1, 1);

		graphics.drawRect(x + 6, y + 5, 1, 1);

		graphics.drawRect(x + 6, y + 4, 1, 1);
		graphics.drawRect(x + 5, y + 4, 1, 1);
		graphics.drawRect(x + 4, y + 4, 1, 1);
		graphics.drawRect(x + 3, y + 4, 1, 1);

		graphics.drawRect(x + 2, y + 3, 1, 1);

		graphics.drawRect(x + 6, y + 2, 1, 1);
		graphics.drawRect(x + 5, y + 2, 1, 1);
		graphics.drawRect(x + 4, y + 2, 1, 1);
		graphics.drawRect(x + 3, y + 2, 1, 1);
	}

	protected void paintSix(Graphics graphics, int x, int y, int width, int height) {
		graphics.setColor(Color.CYAN);
		graphics.drawRect(x + 5, y + 6, 1, 1);
		graphics.drawRect(x + 4, y + 6, 1, 1);
		graphics.drawRect(x + 3, y + 6, 1, 1);

		graphics.drawRect(x + 6, y + 5, 1, 1);

		graphics.drawRect(x + 6, y + 4, 1, 1);
		graphics.drawRect(x + 5, y + 4, 1, 1);
		graphics.drawRect(x + 4, y + 4, 1, 1);
		graphics.drawRect(x + 3, y + 4, 1, 1);

		graphics.drawRect(x + 6, y + 3, 1, 1);
		graphics.drawRect(x + 2, y + 3, 1, 1);

		graphics.drawRect(x + 5, y + 2, 1, 1);
		graphics.drawRect(x + 4, y + 2, 1, 1);
		graphics.drawRect(x + 3, y + 2, 1, 1);
	}

	protected void paintSeven(Graphics graphics, int x, int y, int width, int height) {
		graphics.setColor(Color.BLACK);
		graphics.drawRect(x + 6, y + 6, 1, 1);
		graphics.drawRect(x + 5, y + 6, 1, 1);
		graphics.drawRect(x + 4, y + 6, 1, 1);
		graphics.drawRect(x + 3, y + 6, 1, 1);
		graphics.drawRect(x + 2, y + 6, 1, 1);

		graphics.drawRect(x + 2, y + 5, 1, 1);

		graphics.drawRect(x + 3, y + 4, 1, 1);

		graphics.drawRect(x + 4, y + 3, 1, 1);

		graphics.drawRect(x + 4, y + 2, 1, 1);
	}

	protected void paintEight(Graphics graphics, int x, int y, int width, int height) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.drawRect(x + 5, y + 6, 1, 1);
		graphics.drawRect(x + 4, y + 6, 1, 1);
		graphics.drawRect(x + 3, y + 6, 1, 1);

		graphics.drawRect(x + 6, y + 5, 1, 1);
		graphics.drawRect(x + 2, y + 5, 1, 1);

		graphics.drawRect(x + 5, y + 4, 1, 1);
		graphics.drawRect(x + 4, y + 4, 1, 1);
		graphics.drawRect(x + 3, y + 4, 1, 1);

		graphics.drawRect(x + 6, y + 3, 1, 1);
		graphics.drawRect(x + 2, y + 3, 1, 1);

		graphics.drawRect(x + 5, y + 2, 1, 1);
		graphics.drawRect(x + 4, y + 2, 1, 1);
		graphics.drawRect(x + 3, y + 2, 1, 1);
	}

	protected void paintBomb(Graphics graphics, int x, int y, int width, int height) {
		graphics.setColor(Color.BLACK);
		graphics.drawRect(x + 6, y + 6, 1, 1);
		graphics.drawRect(x + 4, y + 6, 1, 1);
		graphics.drawRect(x + 2, y + 6, 1, 1);

		graphics.drawRect(x + 5, y + 5, 1, 1);
		graphics.drawRect(x + 4, y + 5, 1, 1);
		graphics.drawRect(x + 3, y + 5, 1, 1);

		graphics.drawRect(x + 6, y + 4, 1, 1);
		graphics.drawRect(x + 5, y + 4, 1, 1);
		graphics.drawRect(x + 4, y + 4, 1, 1);
		graphics.drawRect(x + 3, y + 4, 1, 1);
		graphics.drawRect(x + 2, y + 4, 1, 1);

		graphics.drawRect(x + 5, y + 3, 1, 1);
		graphics.drawRect(x + 4, y + 3, 1, 1);
		graphics.drawRect(x + 3, y + 3, 1, 1);

		graphics.drawRect(x + 6, y + 2, 1, 1);
		graphics.drawRect(x + 4, y + 2, 1, 1);
		graphics.drawRect(x + 2, y + 2, 1, 1);
	}

}
