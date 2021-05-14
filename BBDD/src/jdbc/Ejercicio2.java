package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio2 {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://" + "localhost" + "/" + "sakila" + "?" + "user=" + "root"
					+ "&password=" + "Xiongyuxiao991217" + "&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");

			Statement s = conn.createStatement();
			String sqlQuery = "SELECT film_id AS identificador, title AS titulo, release_year AS anio, rental_rate AS precio_de_pelicula, rating AS calificacion\n"
					+ "FROM film AS f\n" + "WHERE f.description LIKE '%Epic%'\n" + "ORDER BY f.rental_rate asc;";

			ResultSet rs = s.executeQuery(sqlQuery);

			//el getColumnaCount empieza con 1
			int resNum = rs.getMetaData().getColumnCount();
			int n = 0;
			while (rs.next()) {
				n++;
				for (int i = 1; i <= resNum; i++) {
					System.out.print(rs.getString(i) + "\t");

				}
				System.out.println();
			}
			System.out.println("El numero de resultados es: "+n);
			System.currentTimeMillis();

			// cerramos los resultados y la sentencia
			rs.close();
			s.close();

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
