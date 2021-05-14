package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {

	private static boolean initialized = false;

	// estatico: solamente pertenece a la clase
	private static String server, port, database, user, password;

	private static java.sql.Connection conn;

	/**
	 * Como el constructor de la clase Un método para configurar la conexión,
	 * recibirá el server, base de datos, user y contraseña de la conexión.
	 * 
	 * @param server
	 * @param port
	 * @param database
	 * @param user
	 * @param password
	 */
	public static void init(String server, String port, String database, String user, String password) {
		// no funciona el this para atributos estaticos
		// por tanto ponemos el nombre de la clase
		DBConnectionManager.server = server;
		DBConnectionManager.port = port;
		DBConnectionManager.database = database;
		DBConnectionManager.user = user;
		DBConnectionManager.password = password;

		initialized = true;
	}

	public static Connection getConnection() {
		// comprobar si la clase esta configurada
		if (!initialized) {
			throw new ExceptionInInitializerError("You should configure connection parameters");
		}
		// comprobar si la conexion esta libre
		// en este ejemplo no va a pasar pq no hay mas de un proceso

		// comprobar si la conexion esta iniciada
		try {
			if (conn == null || conn.isClosed()) {

				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database + "?"
						+ "&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC", user, password);
			}
		} catch (ExceptionInInitializerError e) {
			System.err.println(e.getMessage());
			throw new ExceptionInInitializerError("Error when checking the connection: " + e.getMessage());
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
			throw new ExceptionInInitializerError("Error when connecting with the server: " + ex.getMessage());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
			throw new ExceptionInInitializerError("Error when loading the JDBC Mysql Driver: " + e.getMessage());
		}
		// devolvemos la conexion pedida
		return conn;
	}

	public static void closeConnection() {
		try {
			if(!conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new ExceptionInInitializerError("Error when loading the JDBC Mysql Driver: " + e.getMessage());
		}
	}
}
