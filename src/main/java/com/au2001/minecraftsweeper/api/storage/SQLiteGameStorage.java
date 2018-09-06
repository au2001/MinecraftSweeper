package com.au2001.minecraftsweeper.api.storage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteGameStorage extends DatabaseGameStorage {

	public SQLiteGameStorage(File file) {
		super(getConnection(file), "MinecraftSweeper");
	}

	private static Connection getConnection(File file) {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
