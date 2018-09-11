package com.au2001.minecraftsweeper.plugin;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class BuildStatics {

	public static final MaterialData AIR, BLACK_CONCRETE, BLUE_CONCRETE, CYAN_CONCRETE, GREEN_CONCRETE, LIGHT_BLUE_CONCRETE, LIGHT_GRAY_CONCRETE, ORANGE_CONCRETE, RED_CONCRETE, WHITE_CONCRETE;
	public static final Map<Point, MaterialData> UNKNOWN, FLAG, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, BOMB;

	static { // Materials
		AIR = new MaterialData(Material.AIR);

		MaterialData blackConcrete, blueConcrete, cyanConcrete, greenConcrete, lightBlueConcrete, lightGrayConcrete, orangeConcrete, redConcrete, whiteConcrete;

		try {
			blackConcrete = new MaterialData(Material.BLACK_CONCRETE);
		} catch (NoSuchFieldError e) {
			blackConcrete = new MaterialData(Material.valueOf("CONCRETE"), (byte) 15);
		}
		BLACK_CONCRETE = blackConcrete;

		try {
			blueConcrete = new MaterialData(Material.BLUE_CONCRETE);
		} catch (NoSuchFieldError e) {
			blueConcrete = new MaterialData(Material.valueOf("CONCRETE"), (byte) 11);
		}
		BLUE_CONCRETE = blueConcrete;

		try {
			cyanConcrete = new MaterialData(Material.CYAN_CONCRETE);
		} catch (NoSuchFieldError e) {
			cyanConcrete = new MaterialData(Material.valueOf("CONCRETE"), (byte) 9);
		}
		CYAN_CONCRETE = cyanConcrete;

		try {
			greenConcrete = new MaterialData(Material.GREEN_CONCRETE);
		} catch (NoSuchFieldError e) {
			greenConcrete = new MaterialData(Material.valueOf("CONCRETE"), (byte) 13);
		}
		GREEN_CONCRETE = greenConcrete;

		try {
			lightBlueConcrete = new MaterialData(Material.LIGHT_BLUE_CONCRETE);
		} catch (NoSuchFieldError e) {
			lightBlueConcrete = new MaterialData(Material.valueOf("CONCRETE"), (byte) 3);
		}
		LIGHT_BLUE_CONCRETE = lightBlueConcrete;

		try {
			lightGrayConcrete = new MaterialData(Material.LIGHT_GRAY_CONCRETE);
		} catch (NoSuchFieldError e) {
			lightGrayConcrete = new MaterialData(Material.valueOf("CONCRETE"), (byte) 8);
		}
		LIGHT_GRAY_CONCRETE = lightGrayConcrete;

		try {
			orangeConcrete = new MaterialData(Material.ORANGE_CONCRETE);
		} catch (NoSuchFieldError e) {
			orangeConcrete = new MaterialData(Material.valueOf("CONCRETE"), (byte) 1);
		}
		ORANGE_CONCRETE = orangeConcrete;

		try {
			redConcrete = new MaterialData(Material.RED_CONCRETE);
		} catch (NoSuchFieldError e) {
			redConcrete = new MaterialData(Material.valueOf("CONCRETE"), (byte) 14);
		}
		RED_CONCRETE = redConcrete;

		try {
			whiteConcrete = new MaterialData(Material.WHITE_CONCRETE);
		} catch (NoSuchFieldError e) {
			whiteConcrete = new MaterialData(Material.valueOf("CONCRETE"), (byte) 0);
		}
		WHITE_CONCRETE = whiteConcrete;
	}

	static { // Bomb
		UNKNOWN = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.UNKNOWN.put(new Point(x, 0, z), BuildStatics.WHITE_CONCRETE);
				BuildStatics.UNKNOWN.put(new Point(x, 1, z), BuildStatics.WHITE_CONCRETE);
			}
		}
	}

	static { // TODO: Flag
		FLAG = BuildStatics.UNKNOWN;
	}

	static { // Zero
		ZERO = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.ZERO.put(new Point(x, 0, z), BuildStatics.WHITE_CONCRETE);
				BuildStatics.ZERO.put(new Point(x, 1, z), BuildStatics.AIR);
			}
		}
	}

	static { // One
		ONE = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.ONE.put(new Point(x, 0, z), BuildStatics.WHITE_CONCRETE);
				BuildStatics.ONE.put(new Point(x, 1, z), BuildStatics.AIR);
			}
		}

		BuildStatics.ONE.put(new Point(4, 0, 6), BuildStatics.LIGHT_BLUE_CONCRETE);

		BuildStatics.ONE.put(new Point(5, 0, 5), BuildStatics.LIGHT_BLUE_CONCRETE);
		BuildStatics.ONE.put(new Point(4, 0, 5), BuildStatics.LIGHT_BLUE_CONCRETE);

		BuildStatics.ONE.put(new Point(4, 0, 4), BuildStatics.LIGHT_BLUE_CONCRETE);

		BuildStatics.ONE.put(new Point(4, 0, 3), BuildStatics.LIGHT_BLUE_CONCRETE);

		BuildStatics.ONE.put(new Point(5, 0, 2), BuildStatics.LIGHT_BLUE_CONCRETE);
		BuildStatics.ONE.put(new Point(4, 0, 2), BuildStatics.LIGHT_BLUE_CONCRETE);
		BuildStatics.ONE.put(new Point(3, 0, 2), BuildStatics.LIGHT_BLUE_CONCRETE);
	}

	static { // Two
		TWO = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.TWO.put(new Point(x, 0, z), BuildStatics.WHITE_CONCRETE);
				BuildStatics.TWO.put(new Point(x, 1, z), BuildStatics.AIR);
			}
		}

		BuildStatics.TWO.put(new Point(5, 0, 6), BuildStatics.GREEN_CONCRETE);
		BuildStatics.TWO.put(new Point(4, 0, 6), BuildStatics.GREEN_CONCRETE);
		BuildStatics.TWO.put(new Point(3, 0, 6), BuildStatics.GREEN_CONCRETE);

		BuildStatics.TWO.put(new Point(6, 0, 5), BuildStatics.GREEN_CONCRETE);
		BuildStatics.TWO.put(new Point(2, 0, 5), BuildStatics.GREEN_CONCRETE);

		BuildStatics.TWO.put(new Point(4, 0, 4), BuildStatics.GREEN_CONCRETE);
		BuildStatics.TWO.put(new Point(3, 0, 4), BuildStatics.GREEN_CONCRETE);

		BuildStatics.TWO.put(new Point(5, 0, 3), BuildStatics.GREEN_CONCRETE);

		BuildStatics.TWO.put(new Point(6, 0, 2), BuildStatics.GREEN_CONCRETE);
		BuildStatics.TWO.put(new Point(5, 0, 2), BuildStatics.GREEN_CONCRETE);
		BuildStatics.TWO.put(new Point(4, 0, 2), BuildStatics.GREEN_CONCRETE);
		BuildStatics.TWO.put(new Point(3, 0, 2), BuildStatics.GREEN_CONCRETE);
		BuildStatics.TWO.put(new Point(2, 0, 2), BuildStatics.GREEN_CONCRETE);
	}

	static { // Three
		THREE = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.THREE.put(new Point(x, 0, z), BuildStatics.WHITE_CONCRETE);
				BuildStatics.THREE.put(new Point(x, 1, z), BuildStatics.AIR);
			}
		}

		BuildStatics.THREE.put(new Point(5, 0, 6), BuildStatics.ORANGE_CONCRETE);
		BuildStatics.THREE.put(new Point(4, 0, 6), BuildStatics.ORANGE_CONCRETE);
		BuildStatics.THREE.put(new Point(3, 0, 6), BuildStatics.ORANGE_CONCRETE);

		BuildStatics.THREE.put(new Point(2, 0, 5), BuildStatics.ORANGE_CONCRETE);

		BuildStatics.THREE.put(new Point(4, 0, 4), BuildStatics.ORANGE_CONCRETE);
		BuildStatics.THREE.put(new Point(3, 0, 4), BuildStatics.ORANGE_CONCRETE);

		BuildStatics.THREE.put(new Point(2, 0, 3), BuildStatics.ORANGE_CONCRETE);

		BuildStatics.THREE.put(new Point(5, 0, 2), BuildStatics.ORANGE_CONCRETE);
		BuildStatics.THREE.put(new Point(4, 0, 2), BuildStatics.ORANGE_CONCRETE);
		BuildStatics.THREE.put(new Point(3, 0, 2), BuildStatics.ORANGE_CONCRETE);
	}

	static { // Four
		FOUR = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.FOUR.put(new Point(x, 0, z), BuildStatics.WHITE_CONCRETE);
				BuildStatics.FOUR.put(new Point(x, 1, z), BuildStatics.AIR);
			}
		}

		BuildStatics.FOUR.put(new Point(5, 0, 6), BuildStatics.BLUE_CONCRETE);
		BuildStatics.FOUR.put(new Point(3, 0, 6), BuildStatics.BLUE_CONCRETE);

		BuildStatics.FOUR.put(new Point(6, 0, 5), BuildStatics.BLUE_CONCRETE);
		BuildStatics.FOUR.put(new Point(3, 0, 5), BuildStatics.BLUE_CONCRETE);

		BuildStatics.FOUR.put(new Point(6, 0, 4), BuildStatics.BLUE_CONCRETE);
		BuildStatics.FOUR.put(new Point(5, 0, 4), BuildStatics.BLUE_CONCRETE);
		BuildStatics.FOUR.put(new Point(4, 0, 4), BuildStatics.BLUE_CONCRETE);
		BuildStatics.FOUR.put(new Point(3, 0, 4), BuildStatics.BLUE_CONCRETE);
		BuildStatics.FOUR.put(new Point(2, 0, 4), BuildStatics.BLUE_CONCRETE);

		BuildStatics.FOUR.put(new Point(3, 0, 3), BuildStatics.BLUE_CONCRETE);

		BuildStatics.FOUR.put(new Point(3, 0, 2), BuildStatics.BLUE_CONCRETE);
	}

	static { // Five
		FIVE = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.FIVE.put(new Point(x, 0, z), BuildStatics.WHITE_CONCRETE);
				BuildStatics.FIVE.put(new Point(x, 1, z), BuildStatics.AIR);
			}
		}

		BuildStatics.FIVE.put(new Point(6, 0, 6), BuildStatics.RED_CONCRETE);
		BuildStatics.FIVE.put(new Point(5, 0, 6), BuildStatics.RED_CONCRETE);
		BuildStatics.FIVE.put(new Point(4, 0, 6), BuildStatics.RED_CONCRETE);
		BuildStatics.FIVE.put(new Point(3, 0, 6), BuildStatics.RED_CONCRETE);

		BuildStatics.FIVE.put(new Point(6, 0, 5), BuildStatics.RED_CONCRETE);

		BuildStatics.FIVE.put(new Point(6, 0, 4), BuildStatics.RED_CONCRETE);
		BuildStatics.FIVE.put(new Point(5, 0, 4), BuildStatics.RED_CONCRETE);
		BuildStatics.FIVE.put(new Point(4, 0, 4), BuildStatics.RED_CONCRETE);
		BuildStatics.FIVE.put(new Point(3, 0, 4), BuildStatics.RED_CONCRETE);

		BuildStatics.FIVE.put(new Point(2, 0, 3), BuildStatics.RED_CONCRETE);

		BuildStatics.FIVE.put(new Point(6, 0, 2), BuildStatics.RED_CONCRETE);
		BuildStatics.FIVE.put(new Point(5, 0, 2), BuildStatics.RED_CONCRETE);
		BuildStatics.FIVE.put(new Point(4, 0, 2), BuildStatics.RED_CONCRETE);
		BuildStatics.FIVE.put(new Point(3, 0, 2), BuildStatics.RED_CONCRETE);
	}

	static { // Six
		SIX = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.SIX.put(new Point(x, 0, z), BuildStatics.WHITE_CONCRETE);
				BuildStatics.SIX.put(new Point(x, 1, z), BuildStatics.AIR);
			}
		}

		BuildStatics.SIX.put(new Point(5, 0, 6), BuildStatics.CYAN_CONCRETE);
		BuildStatics.SIX.put(new Point(4, 0, 6), BuildStatics.CYAN_CONCRETE);
		BuildStatics.SIX.put(new Point(3, 0, 6), BuildStatics.CYAN_CONCRETE);

		BuildStatics.SIX.put(new Point(6, 0, 5), BuildStatics.CYAN_CONCRETE);

		BuildStatics.SIX.put(new Point(6, 0, 4), BuildStatics.CYAN_CONCRETE);
		BuildStatics.SIX.put(new Point(5, 0, 4), BuildStatics.CYAN_CONCRETE);
		BuildStatics.SIX.put(new Point(4, 0, 4), BuildStatics.CYAN_CONCRETE);
		BuildStatics.SIX.put(new Point(3, 0, 4), BuildStatics.CYAN_CONCRETE);

		BuildStatics.SIX.put(new Point(6, 0, 3), BuildStatics.CYAN_CONCRETE);
		BuildStatics.SIX.put(new Point(2, 0, 3), BuildStatics.CYAN_CONCRETE);

		BuildStatics.SIX.put(new Point(5, 0, 2), BuildStatics.CYAN_CONCRETE);
		BuildStatics.SIX.put(new Point(4, 0, 2), BuildStatics.CYAN_CONCRETE);
		BuildStatics.SIX.put(new Point(3, 0, 2), BuildStatics.CYAN_CONCRETE);
	}

	static { // Seven
		SEVEN = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.SEVEN.put(new Point(x, 0, z), BuildStatics.WHITE_CONCRETE);
				BuildStatics.SEVEN.put(new Point(x, 1, z), BuildStatics.AIR);
			}
		}

		BuildStatics.SEVEN.put(new Point(6, 0, 6), BuildStatics.BLACK_CONCRETE);
		BuildStatics.SEVEN.put(new Point(5, 0, 6), BuildStatics.BLACK_CONCRETE);
		BuildStatics.SEVEN.put(new Point(4, 0, 6), BuildStatics.BLACK_CONCRETE);
		BuildStatics.SEVEN.put(new Point(3, 0, 6), BuildStatics.BLACK_CONCRETE);
		BuildStatics.SEVEN.put(new Point(2, 0, 6), BuildStatics.BLACK_CONCRETE);

		BuildStatics.SEVEN.put(new Point(2, 0, 5), BuildStatics.BLACK_CONCRETE);

		BuildStatics.SEVEN.put(new Point(3, 0, 4), BuildStatics.BLACK_CONCRETE);

		BuildStatics.SEVEN.put(new Point(4, 0, 3), BuildStatics.BLACK_CONCRETE);

		BuildStatics.SEVEN.put(new Point(4, 0, 2), BuildStatics.BLACK_CONCRETE);
	}

	static { // Eight
		EIGHT = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.EIGHT.put(new Point(x, 0, z), BuildStatics.WHITE_CONCRETE);
				BuildStatics.EIGHT.put(new Point(x, 1, z), BuildStatics.AIR);
			}
		}

		BuildStatics.EIGHT.put(new Point(5, 0, 6), BuildStatics.LIGHT_GRAY_CONCRETE);
		BuildStatics.EIGHT.put(new Point(4, 0, 6), BuildStatics.LIGHT_GRAY_CONCRETE);
		BuildStatics.EIGHT.put(new Point(3, 0, 6), BuildStatics.LIGHT_GRAY_CONCRETE);

		BuildStatics.EIGHT.put(new Point(6, 0, 5), BuildStatics.LIGHT_GRAY_CONCRETE);
		BuildStatics.EIGHT.put(new Point(2, 0, 5), BuildStatics.LIGHT_GRAY_CONCRETE);

		BuildStatics.EIGHT.put(new Point(5, 0, 4), BuildStatics.LIGHT_GRAY_CONCRETE);
		BuildStatics.EIGHT.put(new Point(4, 0, 4), BuildStatics.LIGHT_GRAY_CONCRETE);
		BuildStatics.EIGHT.put(new Point(3, 0, 4), BuildStatics.LIGHT_GRAY_CONCRETE);

		BuildStatics.EIGHT.put(new Point(6, 0, 3), BuildStatics.LIGHT_GRAY_CONCRETE);
		BuildStatics.EIGHT.put(new Point(2, 0, 3), BuildStatics.LIGHT_GRAY_CONCRETE);

		BuildStatics.EIGHT.put(new Point(5, 0, 2), BuildStatics.LIGHT_GRAY_CONCRETE);
		BuildStatics.EIGHT.put(new Point(4, 0, 2), BuildStatics.LIGHT_GRAY_CONCRETE);
		BuildStatics.EIGHT.put(new Point(3, 0, 2), BuildStatics.LIGHT_GRAY_CONCRETE);
	}

	static { // Bomb
		BOMB = new HashMap<Point, MaterialData>();

		for (int x = 1; x < 8; x++) {
			for (int z = 1; z < 8; z++) {
				BuildStatics.BOMB.put(new Point(x, 0, z), BuildStatics.RED_CONCRETE);
				BuildStatics.BOMB.put(new Point(x, 1, z), BuildStatics.AIR);
			}
		}

		BuildStatics.BOMB.put(new Point(6, 0, 6), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(4, 0, 6), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(2, 0, 6), BuildStatics.BLACK_CONCRETE);

		BuildStatics.BOMB.put(new Point(5, 0, 5), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(4, 0, 5), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(3, 0, 5), BuildStatics.BLACK_CONCRETE);

		BuildStatics.BOMB.put(new Point(6, 0, 4), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(5, 0, 4), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(4, 0, 4), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(3, 0, 4), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(2, 0, 4), BuildStatics.BLACK_CONCRETE);

		BuildStatics.BOMB.put(new Point(5, 0, 3), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(4, 0, 3), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(3, 0, 3), BuildStatics.BLACK_CONCRETE);

		BuildStatics.BOMB.put(new Point(6, 0, 2), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(4, 0, 2), BuildStatics.BLACK_CONCRETE);
		BuildStatics.BOMB.put(new Point(2, 0, 2), BuildStatics.BLACK_CONCRETE);
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
