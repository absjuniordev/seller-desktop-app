package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static void createDatabase() {
		Connection connWithoutDb = null;
		Statement st = null;
		try {
			Properties props = loadProperties();
			connWithoutDb = DriverManager.getConnection("jdbc:mysql://localhost:3306/", props.getProperty("user"),
					props.getProperty("password"));

			st = connWithoutDb.createStatement();
			String sql = "CREATE DATABASE IF NOT EXISTS seller";
			st.executeUpdate(sql);
			System.out.println("Banco de dados 'seller' criado ou já existe.");
		} catch (SQLException e) {
			throw new DbException("Erro ao criar banco de dados: " + e.getMessage());
		} finally {
			closeStatement(st);
			closeConnection(connWithoutDb);
		}
	}

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
