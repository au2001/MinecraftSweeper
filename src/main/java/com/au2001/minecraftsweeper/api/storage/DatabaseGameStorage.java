package com.au2001.minecraftsweeper.api.storage;

import java.sql.*;

public class DatabaseGameStorage extends TempGameStorage {

	protected final Connection conn;
	protected final String table;

	public DatabaseGameStorage(Connection conn, String table) {
		super();

		this.conn = conn;
		this.table = table;

		this.load();
	}

	private void load() {
		try {
			Statement statement = this.conn.createStatement();

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + this.table + " (x INT NULLABLE, y INT NULLABLE, sweeped BOOL NOT NULL DEFAULT FALSE, rollbacks INT NOT NULL DEFAULT 0)");

			ResultSet result = statement.executeQuery("SELECT * FROM " + this.table);
			while (result.next()) {
				Integer x = result.getInt("x");
				if (result.wasNull()) x = null;
				Integer y = result.getInt("y");
				if (result.wasNull()) y = null;
				if (x != null && y != null) {
					super.setSweeped(x, y, result.getBoolean("sweeped"));
					super.setRollbackCount(x, y, result.getInt("rollbacks"));
				} else super.setResetCount(result.getInt("rollbacks"));
			}

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setResetCount(long count) {
		super.setResetCount(count);

		try {
			Statement statement = this.conn.createStatement();

			ResultSet result = statement.executeQuery("SELECT * FROM " + this.table + " WHERE `x` IS NULL AND `y` IS NULL");
			String query;
			if (result.next()) query = "UPDATE " + this.table + " SET `rollbacks` = ? WHERE `x` IS NULL AND `y` IS NULL";
			else query = "INSERT INTO " + this.table + " (`x`, `y`, `rollbacks`) VALUES (NULL, NULL, ?)";

			result.close();
			statement.close();

			PreparedStatement preparedStatement = this.conn.prepareStatement(query);
			preparedStatement.setLong(1, count);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setSweeped(int x, int y, boolean sweeped) {
		super.setSweeped(x, y, sweeped);

		try {
			PreparedStatement preparedStatement = this.conn.prepareStatement("SELECT * FROM " + this.table + " WHERE `x` = ? AND `y` = ?");

			preparedStatement.setInt(1, x);
			preparedStatement.setInt(2, y);

			ResultSet result = preparedStatement.executeQuery();
			String query = null;
			if (result.next()) {
				if (result.getInt("rollbacks") != 0)
					query = "UPDATE " + this.table + " SET `sweeped` = ? WHERE `x` = ? AND `y` = ?";
				else query = "DELETE FROM " + this.table + " WHERE `x` = ? AND `y` = ?";
			} else if (sweeped)
				query = "INSERT INTO " + this.table + " (`sweeped`, `x`, `y`) VALUES (?, ?, ?)";

			result.close();
			preparedStatement.close();

			if (query != null) {
				preparedStatement = this.conn.prepareStatement(query);
				if (preparedStatement.getParameterMetaData().getParameterCount() > 2) {
					preparedStatement.setBoolean(1, sweeped);
					preparedStatement.setInt(2, x);
					preparedStatement.setInt(3, y);
				} else {
					preparedStatement.setInt(1, x);
					preparedStatement.setInt(2, y);
				}
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clearSweeped() {
		super.clearSweeped();

		try {
			Statement statement = this.conn.createStatement();

			statement.executeUpdate("UPDATE " + this.table + " SET `sweeped` = FALSE");
			statement.executeUpdate("DELETE FROM " + this.table + " WHERE `sweeped` = FALSE AND `rollbacks` = 0");

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setRollbackCount(int x, int y, long count) {
		super.setRollbackCount(x, y, count);

		try {
			PreparedStatement preparedStatement = this.conn.prepareStatement("SELECT * FROM " + this.table + " WHERE `x` = ? AND `y` = ?");

			preparedStatement.setInt(1, x);
			preparedStatement.setInt(2, y);

			ResultSet result = preparedStatement.executeQuery();
			String query = null;
			if (result.next()) {
				if (result.getBoolean("sweeped"))
					query = "UPDATE " + this.table + " SET `rollbacks` = ? WHERE `x` = ? AND `y` = ?";
				else query = "DELETE FROM " + this.table + " WHERE `x` = ? AND `y` = ?";
			} else if (count != 0)
				query = "INSERT INTO " + this.table + " (`rollbacks`, `x`, `y`) VALUES (?, ?, ?)";

			result.close();
			preparedStatement.close();

			if (query != null) {
				preparedStatement = this.conn.prepareStatement(query);
				if (preparedStatement.getParameterMetaData().getParameterCount() > 2) {
					preparedStatement.setLong(1, count);
					preparedStatement.setInt(2, x);
					preparedStatement.setInt(3, y);
				} else {
					preparedStatement.setInt(1, x);
					preparedStatement.setInt(2, y);
				}
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
