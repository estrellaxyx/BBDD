package jdbc;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio3 {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://" + "localhost" + "/" + "sakila" + "?" + "user=" + "root"
					+ "&password=" + "Xiongyuxiao991217" + "&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Introduce una cadena: ");
			String nombre = sc.nextLine();
			
			Statement s = conn.createStatement();
			String sqlQuery = "SELECT * FROM customer AS c WHERE c.first_name LIKE '"+nombre+"%';";
			
			ResultSet rs = s.executeQuery(sqlQuery);
			
			int resNum = rs.getMetaData().getColumnCount();
			
			while (rs.next()) {
				for (int i = 1; i <= resNum; i++) {
					System.out.print(rs.getString(i) + "\t");

				}
				System.out.println();
			}

			System.currentTimeMillis();
			
			//cerramos los resultados y la sentencia
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
