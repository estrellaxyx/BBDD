package db;

import java.io.File;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Scanner;

import modelo.Cliente;

public class DBClientes {
	public static boolean createTable() {
		boolean result = true;

		Connection c = DBConnectionManager.getConnection();

		String createSentence = "CREATE TABLE `test_bbdd_jdbc`.`clientes` ("
				+ "`id_cliente` INT NOT NULL AUTO_INCREMENT,"// autonumerico
				+ "`nombre` VARCHAR(30) NULL," + "`apellidos` VARCHAR(80) NULL," + "`ciudad` VARCHAR(30) NULL,"
				+ "`distrito` VARCHAR(30) NULL," + "`direccion` VARCHAR(30) NULL," + "`telefono_fijo` VARCHAR(30) NULL,"
				+ "`telefono_movil` VARCHAR(30) NULL," + "PRIMARY KEY (`id_cliente`))";

		try {
			Statement s = c.createStatement();

			s.executeUpdate(createSentence);
			s.close();
		} catch (SQLException ex) {
			result = false;
			System.err.println(ex.getMessage());
		}

		// como solamente teniamos una conexion,
		// podemos quitar el parametro de este metodo
		DBConnectionManager.closeConnection();

		// podemos volver a conectar y cerrar la conexion
		return result;
	}

	public static boolean existTable() {
		boolean result = true;
		Connection c = DBConnectionManager.getConnection();

		String sentence = "SHOW TABLES LIKE 'clientes';";

		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sentence);

			result = rs.next();

			rs.close();
			s.close();
		} catch (SQLException ex) {
			result = false;
			System.err.println(ex.getMessage());
		}

		// como solamente teniamos una conexion,
		// podemos quitar el parametro de este metodo
		DBConnectionManager.closeConnection();

		// podemos volver a conectar y cerrar la conexion
		return result;

	}

	public static int insertarCliente(String nombre, String apellidos, String ciudad, String distrito, String direccion,
			String telefono_fijo, String telefono_movil) {
		Connection c = DBConnectionManager.getConnection();

		int pk = -1;

		String insertSt = "INSERT INTO `clientes`(nombre,apellidos,ciudad,distrito,direccion,telefono_fijo,telefono_movil) "
				+ "VALUES ('" + nombre + "','" + apellidos + "','" + ciudad + "','" + distrito + "','" + direccion
				+ "','" + telefono_fijo + "','" + telefono_movil + "');";

		System.out.println(insertSt);
		try {
			Statement s = c.createStatement();
			
			s.executeUpdate(insertSt, Statement.RETURN_GENERATED_KEYS);
			ResultSet keys = s.getGeneratedKeys();

			keys.next();
			pk = keys.getInt(1);

			keys.close();
			s.close();
		} catch (SQLException ex) {

			System.err.println(ex.getMessage());
		}

		// como solamente teniamos una conexion,
		// podemos quitar el parametro de este metodo
		DBConnectionManager.closeConnection();

		// podemos volver a conectar y cerrar la conexion
		return pk;

	}

	public static int insertarNewClientePrepared(Cliente cl) {
		Connection c = DBConnectionManager.getConnection();

		int pk = -1;

		String insertSt = "INSERT INTO `clientes`(nombre,apellidos,ciudad,distrito,direccion,telefono_fijo,telefono_movil) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";

		System.out.println(insertSt);
		try {
			PreparedStatement s = c.prepareStatement(insertSt, Statement.RETURN_GENERATED_KEYS);

			// pasar los parametros de ?
			s.setString(1, cl.getNombre());
			s.setString(2, cl.getApellidos());
			s.setString(3, cl.getCiudad());
			s.setString(4, cl.getDistrito());
			s.setString(5, cl.getDireccion());
			s.setString(6, cl.getTelefono_fijo());
			s.setString(7, cl.getTelefono_movil());

			s.executeUpdate();

			ResultSet keys = s.getGeneratedKeys();

			keys.next();
			pk = keys.getInt(1);

			cl.setId(pk);

			keys.close();
			s.close();
		} catch (SQLException ex) {

			System.err.println(ex.getMessage());
		}

		// como solamente teniamos una conexion,
		// podemos quitar el parametro de este metodo
		DBConnectionManager.closeConnection();

		// podemos volver a conectar y cerrar la conexion
		return pk;

	}

	public static void insertarVariosClientes() {
		Connection c = DBConnectionManager.getConnection();
		try {
			boolean prevStatusAutoCommit = c.getAutoCommit();
			c.setAutoCommit(false);

			Statement stmt = c.createStatement();

			stmt.addBatch("INSERT INTO clientes (nombre,apellidos) " + "VALUES('John', 'Doe')");
			stmt.addBatch("INSERT INTO clientes (nombre,apellidos) " + "VALUES('John', 'Doe')");
			stmt.addBatch("INSERT INTO clientes (nombre,apellidos) " + "VALUES('John', 'Doe')");
			stmt.addBatch("INSERT INTO clientes (nombre,apellidos) " + "VALUES('John', 'Doe')");
			stmt.addBatch("INSERT INTO clientes (nombre,apellidos) " + "VALUES('John', 'Doe')");

			int[] filasAfectadas = stmt.executeBatch();

			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as numClientes FROM clientes WHERE nombre='John'");
			if (rs.next())
				System.out.println("Numero de clientes insertados: " + rs.getInt(1));

			c.rollback();

			c.setAutoCommit(prevStatusAutoCommit);

			DBConnectionManager.closeConnection();

		} catch (SQLException ex) {

			System.err.println(ex.getMessage());
		}

	}

	public static LinkedList<Cliente> buscarCliente(String nombreOApellido) {
		LinkedList<Cliente> res = new LinkedList<Cliente>();

		Connection conn = DBConnectionManager.getConnection();

		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT * FROM clientes WHERE nombre LIKE ? OR apellidos LIKE ?");
			pst.setString(1, '%' + nombreOApellido + '%');
			pst.setString(2, '%' + nombreOApellido + '%');

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("ciudad"),
						rs.getString("distrito"), rs.getString("direccion"), rs.getString("telefono_fijo"),
						rs.getString("telefono_movil"));

				c.setId(rs.getInt(1));

				res.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBConnectionManager.closeConnection();

		return res;
	}

	public static void importarCSV(String pathFichero) {
		try {
			Scanner sc = new Scanner(new File(pathFichero), "UTF-8");

			String regEx = ",(?=([^\"]|\"[^\"]*\")*$)";
			sc.nextLine();
			while (sc.hasNext()) {
				String lineaDeDatos = sc.nextLine();
				String[] columnas = lineaDeDatos.split(regEx);

				for (int i = 0; i < columnas.length; i++) {
					if (columnas[i].startsWith("\"") && columnas[i].endsWith("\""))
						columnas[i] = columnas[i].substring(1, columnas[i].length() - 1);

					System.out.print(columnas[i] + "\t");
				}
				System.out.println();
			}

			sc.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
