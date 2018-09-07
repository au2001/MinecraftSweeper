package com.au2001.minecraftsweeper.plugin;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class BuildStatics {

	public static final Map<Point, Material> UNKNOWN, FLAG, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, BOMB;

	static { // Bomb
		UNKNOWN = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				UNKNOWN.put(new Point(x, 0, z), Material.WHITE_CONCRETE);
				UNKNOWN.put(new Point(x, 1, z), Material.WHITE_CONCRETE);
			}
		}
	}

	static { // TODO: Flag
		FLAG = UNKNOWN;
	}

	static { // Zero
		ZERO = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				ZERO.put(new Point(x, 0, z), Material.WHITE_CONCRETE);
				ZERO.put(new Point(x, 1, z), Material.AIR);
			}
		}
	}

	static { // One
		ONE = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				ONE.put(new Point(x, 0, z), Material.WHITE_CONCRETE);
				ONE.put(new Point(x, 1, z), Material.AIR);
			}
		}

		ONE.put(new Point(4, 0, 6), Material.LIGHT_BLUE_CONCRETE);

		ONE.put(new Point(5, 0, 5), Material.LIGHT_BLUE_CONCRETE);
		ONE.put(new Point(4, 0, 5), Material.LIGHT_BLUE_CONCRETE);

		ONE.put(new Point(4, 0, 4), Material.LIGHT_BLUE_CONCRETE);

		ONE.put(new Point(4, 0, 3), Material.LIGHT_BLUE_CONCRETE);

		ONE.put(new Point(5, 0, 2), Material.LIGHT_BLUE_CONCRETE);
		ONE.put(new Point(4, 0, 2), Material.LIGHT_BLUE_CONCRETE);
		ONE.put(new Point(3, 0, 2), Material.LIGHT_BLUE_CONCRETE);
	}

	static { // Two
		TWO = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				TWO.put(new Point(x, 0, z), Material.WHITE_CONCRETE);
				TWO.put(new Point(x, 1, z), Material.AIR);
			}
		}

		TWO.put(new Point(5, 0, 6), Material.GREEN_CONCRETE);
		TWO.put(new Point(4, 0, 6), Material.GREEN_CONCRETE);
		TWO.put(new Point(3, 0, 6), Material.GREEN_CONCRETE);

		TWO.put(new Point(6, 0, 5), Material.GREEN_CONCRETE);
		TWO.put(new Point(2, 0, 5), Material.GREEN_CONCRETE);

		TWO.put(new Point(4, 0, 4), Material.GREEN_CONCRETE);
		TWO.put(new Point(3, 0, 4), Material.GREEN_CONCRETE);

		TWO.put(new Point(5, 0, 3), Material.GREEN_CONCRETE);

		TWO.put(new Point(6, 0, 2), Material.GREEN_CONCRETE);
		TWO.put(new Point(5, 0, 2), Material.GREEN_CONCRETE);
		TWO.put(new Point(4, 0, 2), Material.GREEN_CONCRETE);
		TWO.put(new Point(3, 0, 2), Material.GREEN_CONCRETE);
		TWO.put(new Point(2, 0, 2), Material.GREEN_CONCRETE);
	}

	static { // Three
		THREE = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				THREE.put(new Point(x, 0, z), Material.WHITE_CONCRETE);
				THREE.put(new Point(x, 1, z), Material.AIR);
			}
		}

		THREE.put(new Point(5, 0, 6), Material.ORANGE_CONCRETE);
		THREE.put(new Point(4, 0, 6), Material.ORANGE_CONCRETE);
		THREE.put(new Point(3, 0, 6), Material.ORANGE_CONCRETE);

		THREE.put(new Point(2, 0, 5), Material.ORANGE_CONCRETE);

		THREE.put(new Point(4, 0, 4), Material.ORANGE_CONCRETE);
		THREE.put(new Point(3, 0, 4), Material.ORANGE_CONCRETE);

		THREE.put(new Point(2, 0, 3), Material.ORANGE_CONCRETE);

		THREE.put(new Point(5, 0, 2), Material.ORANGE_CONCRETE);
		THREE.put(new Point(4, 0, 2), Material.ORANGE_CONCRETE);
		THREE.put(new Point(3, 0, 2), Material.ORANGE_CONCRETE);
	}

	static { // Four
		FOUR = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				FOUR.put(new Point(x, 0, z), Material.WHITE_CONCRETE);
				FOUR.put(new Point(x, 1, z), Material.AIR);
			}
		}

		FOUR.put(new Point(5, 0, 6), Material.BLUE_CONCRETE);
		FOUR.put(new Point(3, 0, 6), Material.BLUE_CONCRETE);

		FOUR.put(new Point(6, 0, 5), Material.BLUE_CONCRETE);
		FOUR.put(new Point(3, 0, 5), Material.BLUE_CONCRETE);

		FOUR.put(new Point(6, 0, 4), Material.BLUE_CONCRETE);
		FOUR.put(new Point(5, 0, 4), Material.BLUE_CONCRETE);
		FOUR.put(new Point(4, 0, 4), Material.BLUE_CONCRETE);
		FOUR.put(new Point(3, 0, 4), Material.BLUE_CONCRETE);
		FOUR.put(new Point(2, 0, 4), Material.BLUE_CONCRETE);

		FOUR.put(new Point(3, 0, 3), Material.BLUE_CONCRETE);

		FOUR.put(new Point(3, 0, 2), Material.BLUE_CONCRETE);
	}

	static { // Five
		FIVE = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				FIVE.put(new Point(x, 0, z), Material.WHITE_CONCRETE);
				FIVE.put(new Point(x, 1, z), Material.AIR);
			}
		}

		FIVE.put(new Point(6, 0, 6), Material.RED_CONCRETE);
		FIVE.put(new Point(5, 0, 6), Material.RED_CONCRETE);
		FIVE.put(new Point(4, 0, 6), Material.RED_CONCRETE);
		FIVE.put(new Point(3, 0, 6), Material.RED_CONCRETE);

		FIVE.put(new Point(6, 0, 5), Material.RED_CONCRETE);

		FIVE.put(new Point(6, 0, 4), Material.RED_CONCRETE);
		FIVE.put(new Point(5, 0, 4), Material.RED_CONCRETE);
		FIVE.put(new Point(4, 0, 4), Material.RED_CONCRETE);
		FIVE.put(new Point(3, 0, 4), Material.RED_CONCRETE);

		FIVE.put(new Point(2, 0, 3), Material.RED_CONCRETE);

		FIVE.put(new Point(6, 0, 2), Material.RED_CONCRETE);
		FIVE.put(new Point(5, 0, 2), Material.RED_CONCRETE);
		FIVE.put(new Point(4, 0, 2), Material.RED_CONCRETE);
		FIVE.put(new Point(3, 0, 2), Material.RED_CONCRETE);
	}

	static { // Six
		SIX = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				SIX.put(new Point(x, 0, z), Material.WHITE_CONCRETE);
				SIX.put(new Point(x, 1, z), Material.AIR);
			}
		}

		SIX.put(new Point(5, 0, 6), Material.CYAN_CONCRETE);
		SIX.put(new Point(4, 0, 6), Material.CYAN_CONCRETE);
		SIX.put(new Point(3, 0, 6), Material.CYAN_CONCRETE);

		SIX.put(new Point(6, 0, 5), Material.CYAN_CONCRETE);

		SIX.put(new Point(6, 0, 4), Material.CYAN_CONCRETE);
		SIX.put(new Point(5, 0, 4), Material.CYAN_CONCRETE);
		SIX.put(new Point(4, 0, 4), Material.CYAN_CONCRETE);
		SIX.put(new Point(3, 0, 4), Material.CYAN_CONCRETE);

		SIX.put(new Point(6, 0, 3), Material.CYAN_CONCRETE);
		SIX.put(new Point(2, 0, 3), Material.CYAN_CONCRETE);

		SIX.put(new Point(5, 0, 2), Material.CYAN_CONCRETE);
		SIX.put(new Point(4, 0, 2), Material.CYAN_CONCRETE);
		SIX.put(new Point(3, 0, 2), Material.CYAN_CONCRETE);
	}

	static { // Seven
		SEVEN = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				SEVEN.put(new Point(x, 0, z), Material.WHITE_CONCRETE);
				SEVEN.put(new Point(x, 1, z), Material.AIR);
			}
		}

		SEVEN.put(new Point(6, 0, 6), Material.BLACK_CONCRETE);
		SEVEN.put(new Point(5, 0, 6), Material.BLACK_CONCRETE);
		SEVEN.put(new Point(4, 0, 6), Material.BLACK_CONCRETE);
		SEVEN.put(new Point(3, 0, 6), Material.BLACK_CONCRETE);
		SEVEN.put(new Point(2, 0, 6), Material.BLACK_CONCRETE);

		SEVEN.put(new Point(2, 0, 5), Material.BLACK_CONCRETE);

		SEVEN.put(new Point(3, 0, 4), Material.BLACK_CONCRETE);

		SEVEN.put(new Point(4, 0, 3), Material.BLACK_CONCRETE);

		SEVEN.put(new Point(4, 0, 2), Material.BLACK_CONCRETE);
	}

	static { // Eight
		EIGHT = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				EIGHT.put(new Point(x, 0, z), Material.WHITE_CONCRETE);
				EIGHT.put(new Point(x, 1, z), Material.AIR);
			}
		}

		EIGHT.put(new Point(5, 0, 6), Material.LIGHT_GRAY_CONCRETE);
		EIGHT.put(new Point(4, 0, 6), Material.LIGHT_GRAY_CONCRETE);
		EIGHT.put(new Point(3, 0, 6), Material.LIGHT_GRAY_CONCRETE);

		EIGHT.put(new Point(6, 0, 5), Material.LIGHT_GRAY_CONCRETE);
		EIGHT.put(new Point(2, 0, 5), Material.LIGHT_GRAY_CONCRETE);

		EIGHT.put(new Point(5, 0, 4), Material.LIGHT_GRAY_CONCRETE);
		EIGHT.put(new Point(4, 0, 4), Material.LIGHT_GRAY_CONCRETE);
		EIGHT.put(new Point(3, 0, 4), Material.LIGHT_GRAY_CONCRETE);

		EIGHT.put(new Point(6, 0, 3), Material.LIGHT_GRAY_CONCRETE);
		EIGHT.put(new Point(2, 0, 3), Material.LIGHT_GRAY_CONCRETE);

		EIGHT.put(new Point(5, 0, 2), Material.LIGHT_GRAY_CONCRETE);
		EIGHT.put(new Point(4, 0, 2), Material.LIGHT_GRAY_CONCRETE);
		EIGHT.put(new Point(3, 0, 2), Material.LIGHT_GRAY_CONCRETE);
	}

	static { // Bomb
		BOMB = new HashMap<Point, Material>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BOMB.put(new Point(x, 0, z), Material.RED_CONCRETE);
				BOMB.put(new Point(x, 1, z), Material.AIR);
			}
		}

		BOMB.put(new Point(6, 0, 6), Material.BLACK_CONCRETE);
		BOMB.put(new Point(4, 0, 6), Material.BLACK_CONCRETE);
		BOMB.put(new Point(2, 0, 6), Material.BLACK_CONCRETE);

		BOMB.put(new Point(5, 0, 5), Material.BLACK_CONCRETE);
		BOMB.put(new Point(4, 0, 5), Material.BLACK_CONCRETE);
		BOMB.put(new Point(3, 0, 5), Material.BLACK_CONCRETE);

		BOMB.put(new Point(6, 0, 4), Material.BLACK_CONCRETE);
		BOMB.put(new Point(5, 0, 4), Material.BLACK_CONCRETE);
		BOMB.put(new Point(4, 0, 4), Material.BLACK_CONCRETE);
		BOMB.put(new Point(3, 0, 4), Material.BLACK_CONCRETE);
		BOMB.put(new Point(2, 0, 4), Material.BLACK_CONCRETE);

		BOMB.put(new Point(5, 0, 3), Material.BLACK_CONCRETE);
		BOMB.put(new Point(4, 0, 3), Material.BLACK_CONCRETE);
		BOMB.put(new Point(3, 0, 3), Material.BLACK_CONCRETE);

		BOMB.put(new Point(6, 0, 2), Material.BLACK_CONCRETE);
		BOMB.put(new Point(4, 0, 2), Material.BLACK_CONCRETE);
		BOMB.put(new Point(2, 0, 2), Material.BLACK_CONCRETE);
	}

	public static class Point {

		public final int x, y, z;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public boolean equals(Object other) {
			if (!(other instanceof Point)) return false;
			if (((Point) other).x != this.x) return false;
			if (((Point) other).y != this.y) return false;
			if (((Point) other).z != this.z) return false;
			return true;
		}

		public int hashCode() {
			int hash = 3;
			hash = 19 * hash + this.x;
			hash = 19 * hash + this.y;
			hash = 19 * hash + this.z;
			return hash;
		}
	}

}
